package controllers;

import models.WrapperSqlService;
import models.XPathToSQL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import play.libs.XML;
import play.mvc.Controller;
import play.mvc.Result;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.sql.*;

import static play.libs.Json.*;

/**
 * Created on 15/01/15.
 */
public class Wrapper extends Controller {

    public static Result doRequest(String xpath){

        try {
            XPathToSQL e = new XPathToSQL(xpath);
            Connection con = WrapperSqlService.getInstance().getConnection();
            Statement s = con.createStatement();
            return ok(getStringFromDoc(toDocument(s.executeQuery(e.sqlRequest)))).as("application/xml");
        }catch (Exception e){
            return badRequest(toJson(e));
        }

    }

    public static Result ping(){
        return ok(toJson(new Object(){
            public String status = "ok";
        }));
    }

    public static Document toDocument(ResultSet rs)
            throws ParserConfigurationException, SQLException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder        = factory.newDocumentBuilder();
        Document doc                   = builder.newDocument();

        Element results = doc.createElement("results");
        doc.appendChild(results);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount           = rsmd.getColumnCount();

        while (rs.next())
        {
            Element row = doc.createElement("row");
            results.appendChild(row);

            for (int i = 1; i <= colCount; i++)
            {
                String columnName = rsmd.getColumnName(i);
                Object value      = rs.getObject(i);
                if(value != null){
                    Element node      = doc.createElement(columnName);
                    node.appendChild(doc.createTextNode(value.toString()));
                    row.appendChild(node);
                }
            }
        }
        return doc;
    }

    public static String getStringFromDoc(org.w3c.dom.Document doc)    {
        try
        {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            writer.flush();
            return writer.toString();
        }
        catch(TransformerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
