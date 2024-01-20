/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.utils.utils;

/**
 * thrown when an invalid sequence like \g is encountered while parsing a symbol
 */
public class StringFormatException extends Exception { static final long serialVersionUID =30101L;

  public StringFormatException() {
    super();
  }

  public StringFormatException(String mess) {
    super(mess);
  }

  public StringFormatException(String mess,Throwable cause) {
    super(mess,cause);
  }

  public StringFormatException(Throwable cause) {
    super(cause);
  }

}
