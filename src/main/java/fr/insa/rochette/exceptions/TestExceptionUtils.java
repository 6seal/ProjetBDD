/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francois
 */
public class TestExceptionUtils {

    public static void test() {
        try {
            throw new RuntimeException("coucou");
        } catch (Exception ex) {
            System.out.println(ExceptionUtils.messageEtPremiersAppelsDansPackage(ex, "fr.insa.rochette", 5));
        }
        throw new RuntimeException("coucou2");
    }

    public static void main(String[] args) {
        test();
    }

}