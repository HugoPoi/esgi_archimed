package models;

import org.w3c.xpath.parser.*;

import java.io.StringReader;

/**
 * Created on 30/01/15.
 */
public class XPathToSQL {

    public static String sqlFromXPath(String xpathStr) throws ParseException{

        StringBuilder sql = new StringBuilder();
        //XPath parser = new XPath(new StringReader(xpathStr));
        //SimpleNode parsedTree = parser.XPath2();
        //source3/wp_comments[@comment_author='admin' and @comment_karma=0]/@comment_ID
        //((SimpleNode) parsedTree.jjtGetChild(0)).dump("##");
        String[] parts = xpathStr.split("/");

        System.out.println(parts[1]);
        System.out.println(parts[2].split("\\[")[1].split("\\]")[0]);
        System.out.println(parts[3]);

        return sql.toString();
    }



}
