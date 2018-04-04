
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitry
 */
public class Matrix
{
    private int matrixOrder;
    private final int lowerBound = 2;
    private final int upperBound = 100;
    
    public Matrix(String matrixOrder)
            throws MyException
    {
        setMatrixOrder(matrixOrder);
    } // Matrix
    
    public int getMatrixOrder()
    {
        return matrixOrder;
    } // getMatrixOrder
    
    public void setMatrixOrder(String matrixOrder)
            throws MyException
    {
        if (!IntegertryParse(matrixOrder))
        {
            throw new MyException("Введено некорректное значение");
        } // if
        this.matrixOrder = Integer.parseInt(matrixOrder);
        
        if (this.matrixOrder < lowerBound | this.matrixOrder > upperBound)
        {
            throw new MyException("Введено некорректное значение");
        } // if
    } // setMatrixOrder
    
    private boolean IntegertryParse(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        } // try
        catch (NullPointerException|NumberFormatException exception)
        {
            return false;
        } // catch
    } // IntegertryParse
}
