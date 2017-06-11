/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

/**
 *
 * @author Roger Boza
 */
public class InvalidBookException extends RuntimeException{

    public InvalidBookException(String string) {
        super(string);
    }
    
}
