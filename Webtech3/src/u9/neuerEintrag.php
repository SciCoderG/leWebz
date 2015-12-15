<?php
session_start();
?>
<?php
$name = filter_input(INPUT_POST, 'name');
$email = filter_input(INPUT_POST, 'email');
$input = filter_input(INPUT_POST, 'input');

if (!(empty($name) || empty($email) || empty($input))) {
    $newEntry = new Eintrag($name, $email, $input);
    $_SESSION['eintraege'][] = $newEntry;
    addToGuestbook($newEntry->ausgabe());
}

function __autoload($class_name) {
    include $class_name . '.php';
}

function addToGuestbook($eintragAusgabe) {
    $filename = "eintraege.txt";
    $file = fopen($filename, "a") or die("Kann Datei nicht zum Schreiben öffnen");
    fwrite($file, $eintragAusgabe);
    fclose($file);
}
?>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gästebuch - neuer Eintrag!</title>

    </head>

    <body>
        <header> 
            <h1>Eintrag wurde erstellt!</h1>
        </header>
        <section>
            <h3>Ihre bisherigen Einträge:</h3>

            <p>
                Sie können auch die <a href="alleEintraege.php">Liste aller Gästebucheinträge</a> 
                einsehen, oder einen <a href="index.html">weiteren Eintrag</a> erstellen
            </p>



            <?php
            foreach ($_SESSION['eintraege'] as $eintrag) {
                print ($eintrag->ausgabe());
            }
            ?>
        </section>
    </body>
</html>
