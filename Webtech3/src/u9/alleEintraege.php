<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gästebuch - alle Einträge!</title>
    </head>
    <body>
        <header> 
            <h1>Alle Gästebucheinträge:</h1>
        </header>
        <section>
            <h3>Alle bisherigen Einträge:</h3>
            <p>Sie können auch einen <a href="index.html">neuen Eintrag erstellen</a> 
                oder ihre <a href="neuerEintrag.php">bisherigen Einträge</a> einsehen.
            </p>

            <?php
            include ('eintraege.txt');
            ?>

        </section>

    </body>
</html>
