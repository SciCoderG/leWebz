package u3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class SimpleProxy implements Runnable
{
    private ServerSocket serverSocket;

    private Socket proxySocket;

    private int port;

    private Socket clientSocket;

    /**
     * Basically creates a Serversocket with the specified port
     * 
     * @param port
     */
    public SimpleProxy(int port)
    {
        this.port = port;
        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e)
        {
            System.out.println("Creating ServerSocket: " + e.getMessage());
        }
    }

    @Override
    public void run()
    {
        this.start();
    }

    /**
     * waits for a client to connect, then parses the client input and returns
     * the wanted stuff
     */
    public void start()
    {
        try
        {
            System.out.println("Creating client socket.");
            clientSocket = serverSocket.accept();
            System.out.println("Connected to client socket.");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientRequest;
            while (null != (clientRequest = in.readLine()))
            {
                if (clientRequest.isEmpty())
                {
                    break;
                }
                processClientInput(clientRequest);
            }
            clientSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * If we get a HTTP GET, read out the url and show it back to the client.
     * This code is ugly as hell.
     * 
     * @param input
     */
    private void processClientInput(String input)
    {
        System.out.println("Processing input: " + input);
        String url;
        input = input.trim();
        if (Pattern.matches("GET /.* HTTP/1.1", input))
        {
            System.out.println("Found the GET Request. Starting to filter out URL.");
            // filter out the url
            url = input.substring(5, input.indexOf("HTTP/1.1"));
            System.out.println("Filtered out url: " + url);
            try
            {
                int httpIndex = url.indexOf("//") + 2;
                String host = url;
                if (httpIndex != 1)
                {
                    host = url.substring(httpIndex);
                }

                int indexAfterSlash = host.indexOf('/');
                String afterSlash;
                if (indexAfterSlash != -1)
                {
                    afterSlash = host.substring(indexAfterSlash);
                    host = host.substring(0, indexAfterSlash);
                }
                else
                {
                    afterSlash = host;
                }

                System.out.println("Found requested host: " + host);
                proxySocket = new Socket(host, port);

                DataOutputStream dout = new DataOutputStream(proxySocket.getOutputStream());
                BufferedReader din = new BufferedReader(new InputStreamReader(proxySocket.getInputStream()));

                dout.writeBytes("GET " + afterSlash + " HTTP/1.1\n");
                dout.writeBytes("HOST: " + host + "\n");
                dout.writeByte('\n');

                // receive result
                DataOutputStream doutClient = new DataOutputStream(clientSocket.getOutputStream());

                System.out.println("Completed Request to host, filtering out images.");
                String serverInput;
                try
                {

                    while (din != null && null != (serverInput = din.readLine()))
                    {
                        doutClient.writeBytes(filterOutImages(serverInput));
                        if (serverInput.endsWith("</html>"))
                        {
                            clientSocket.close();
                            break;
                        }
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

    private String filterOutImages(String input)
    {

        System.out.println("Reading line: " + input);
        String output = input;
        if (Pattern.matches(".*<img.*/>.*", input))
        {
            int indexOfImg = input.indexOf("<img");
            output = input.substring(0, indexOfImg);
            // Filter out the alternative description
            String alt = input.substring(input.indexOf("alt=") + 5);
            alt = alt.substring(0, alt.indexOf('\"'));

            output += alt;
            output += input.substring(input.indexOf("/>", indexOfImg) + 2);
            System.out.println("Found image, converted line to: " + output);
        }
        output += "\n";
        return output;

    }

    public static void main(String[] args)
    {
        Thread thread = new Thread(new SimpleProxy(80));
        System.out.println("Created Thread");
        thread.run();
    }

}
