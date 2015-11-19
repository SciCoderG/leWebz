package u3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class EvenSimplerProxy implements Runnable
{
    public static void main(String[] args)
    {
        Thread thread = new Thread(new EvenSimplerProxy());
        thread.run();
    }

    @Override
    public void run()
    {
        int port = 4221;
        try
        {
            // Create a server socket on port 
            ServerSocket proxyServerSocket = new ServerSocket(port);
            // wait until browser connects to server socket
            Socket browser = proxyServerSocket.accept();
            // Open a DataOutPutStream to browser
            DataOutputStream doutBrowser = new DataOutputStream(browser.getOutputStream());

            // Connect to public.hoschule-trier.de
            String host = "public.hochschule-trier.de";
            String afterSlash = "/~schneidg/convert.htm";
            Socket proxySocket = new Socket(host, 80);
            // Send the GET Request
            DataOutputStream toServer = new DataOutputStream(proxySocket.getOutputStream());
            toServer.writeBytes("GET " + afterSlash + " HTTP/1.1\n");
            toServer.writeBytes("HOST: " + host + "\n");
            toServer.writeByte('\n');

            // Receive results
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(proxySocket.getInputStream()));
            String result;
            while (null != (result = fromServer.readLine()))
            {
                doutBrowser.writeBytes(filterOutImages(result));
            }
            proxySocket.close();
            browser.close();
            proxyServerSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String filterOutImages(String input)
    {
        // System.out.println("Reading line: " + input);
        String output = input;
        if (Pattern.matches(".*<img.*/>.*", input))
        {
            int indexOfImg = input.indexOf("<img");
            output = input.substring(0, indexOfImg);
            // Filter out the alternative description
            String alt = input.substring(input.indexOf("alt=\"") + 5);
            alt = alt.substring(0, alt.indexOf('\"'));

            output += alt;
            output += input.substring(input.indexOf("/>", indexOfImg) + 2);
            // System.out.println("Found image, converted line to: " + output);
        }
        output += "\n";
        return output;

    }

}
