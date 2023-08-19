import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;

public class XMLParser 
{
    public static void main(String[] args) 
    {
        try 
        {
            File inputFile = new File("healthcare_data.xml"); // Replace with your XML file path
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Patients");

            String[][] tableData = new String[nodeList.getLength()][4]; // Assuming four columns

            for (int i = 0; i < nodeList.getLength(); i++) 
            {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element element = (Element) node;
                    String patientName = element.getElementsByTagName("patientName").item(0).getTextContent();
                    String age = element.getElementsByTagName("age").item(0).getTextContent();
                    String diagnosis = element.getElementsByTagName("description").item(0).getTextContent();
                    tableData[i][0] = patientName;
                    tableData[i][1] = age;
                    tableData[i][2] = diagnosis;

                }
            }
            generateHTMLTable(tableData, "table.html");
            System.out.println("HTML table generated successfully.");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public static void generateHTMLTable(String[][] data, String outputFilePath) 
    {
        try 
        {
            FileWriter writer = new FileWriter(outputFilePath);

            writer.write("<html><body>");
            writer.write("<table border=\"1\">");

            // Generate table header
            writer.write("<tr>");
            writer.write("<th>patientName</th>");
            writer.write("<th>age</th>");
            writer.write("<th>diagnosis</th>");
            writer.write("</tr>");

            // Generate table rows
            for (String[] row : data) 
            {
                writer.write("<tr>");
                for (String cell : row) 
                {
                    writer.write("<td>" + cell + "</td>");
                }
                writer.write("</tr>");
            }

            writer.write("</table>");
            writer.write("</body></html>");

            writer.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}