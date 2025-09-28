package com.myapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Optional;

/* 
 * class Charater
 */
public class Game {
    public Game(){}

        public static void main(String[] args) {
            Character warrior = new Warrior("Chiến Binh");
            Character mage = new Mage("Pháp Sư");
            Priest priest = new Priest("Giáo Sĩ");

            warrior.attack(mage);
            mage.attack(warrior);
            priest.heal(warrior);
            priest.attack(mage);

            System.out.println("\nTrạng thái sau giao tranh:");
            warrior.displayStatus();
            mage.displayStatus();
            priest.displayStatus();
        }

}






