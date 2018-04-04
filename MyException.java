
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitry
 */
public class MyException extends Exception
{
    public MyException(String message)
    {
        JOptionPane.showMessageDialog(
                null, message,
                "Исключение", 0);
    } // MyException
}
