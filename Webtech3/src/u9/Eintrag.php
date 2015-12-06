<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Eintrag
 *
 * @author David_000
 */
class Eintrag {

    private $name;
    private $email;
    private $text;

    function __construct($nameValue, $emailValue, $textValue) {
        $this->name = $nameValue;
        $this->email = $emailValue;
        $this->text = $textValue;
    }

    public function ausgabe() {
        return ("<p>\n<b>Name:</b> $this->name <br> \n<b>Email:</b> $this->email "
                . "<br> \n<b>Nachricht:</b> \n <br>$this->text \n" );
    }

    public function getName() {
        return $this->name;
    }

}

?>
