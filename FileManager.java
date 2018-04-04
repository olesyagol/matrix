
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitry
 */
public class FileManager
{
    private JFileChooser fileChooser;
    private String filePath;
    
    public FileManager()
    {
        fileChooser = new JFileChooser();
    } // FileManager
    
    public String getFilePath()
    {
        return filePath;
    } // getFilePath
    
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    } // setFilePath
    
    public boolean runFileChooser(String dialogTitle, String approveButtonText)
    {
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setCurrentDirectory(new File("."));
        
        fileChooser.showDialog(null, approveButtonText);
        
        File selectedFile = fileChooser.getSelectedFile();
        
        if (selectedFile == null)
        {
            return false;
        } // if
        
        filePath = selectedFile.getAbsolutePath();
        return true;
    } // openFileChooser
    
    public void saveTextFile(JTable jTable)
            throws IOException, MyException
    {
        if (!runFileChooser("Сохранение файла", "Save"))
        {
            throw new MyException("Файл не выбран");
        } // if
        
        BufferedWriter bufferedWriter = null;
        
        try
        {
            FileWriter fileWriter = new FileWriter(filePath);
            bufferedWriter = new BufferedWriter(fileWriter);
            
            DefaultTableModel model = (DefaultTableModel)jTable.getModel();
            
            int nRows = jTable.getRowCount();
            int nColumns = jTable.getColumnCount();
            
            for (int i = 0; i < nRows; i++)
            {
                for (int j = 0; j < nColumns; j++)
                {
                    bufferedWriter.write(jTable.getValueAt(i, j).toString() + " ");
                } // for
                bufferedWriter.newLine();
            } // for
        } // catch
        finally
        {
            if (bufferedWriter != null)
            {
                bufferedWriter.close();
            } // if
        } // finally
    } // saveTextFile
    
    public void loadTextFile(JTable jTable)
            throws IOException, MyException
    {
        if (!runFileChooser("Загрузка файла", "Load"))
        {
            throw new MyException("Файл не выбран");
        } // if
        
        BufferedReader bufferedReader = null;
        
        try
        {
            FileReader fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            
            DefaultTableModel model = (DefaultTableModel)jTable.getModel();
            
            String line = "";
            String [] subLines = {};
            String regExp = "\\s";
            
            boolean isFirst = true;
            
            while ((line = bufferedReader.readLine()) != null)
            {
                subLines = line.split(regExp);
                
                if (isFirst == true)
                {
                    model.setColumnCount(subLines.length);
                    isFirst = false;
                } // if
                
                model.addRow(subLines);
                
//                for (int i = 0; i < subLines.length; i++)
//                {
//                    for (int j = 0; j < subLines.length; j++)
//                    {
//                        jTable.setValueAt(subLines[j], i, j);
//                    } // for
//                } // for
            } // while
        } // try
        finally
        {
            if (bufferedReader != null)
            {
                bufferedReader.close();
            } // if
        } // finally   
    } // loadTextFile
}
