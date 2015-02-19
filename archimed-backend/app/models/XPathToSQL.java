package models;

import org.w3c.xpath.parser.*;

import java.io.StringReader;

/**
 * Created on 30/01/15.
 */
public class XPathToSQL {

    public String table;
    public String wrapper;
    public String whereClause;
    public String sqlRequest;
    public String field;

    public XPathToSQL(String xpathStr) throws ParseException{

        StringBuilder sql = new StringBuilder();
        //XPath parser = new XPath(new StringReader(xpathStr));
        //SimpleNode parsedTree = parser.XPath2();
        //source3/wp_comments[@comment_author='admin' and @comment_karma=0]/@comment_ID
        //((SimpleNode) parsedTree.jjtGetChild(0)).dump("##");
        String[] parts = xpathStr.split("/");

        wrapper = parts[1];
        try{
            table = parts[2].split("\\[")[0];
            String where = parts[2].split("\\[")[1].split("\\]")[0];
            whereClause = "WHERE " + where.replaceAll("@","");

        }catch(ArrayIndexOutOfBoundsException e){
            whereClause = "";
        }
        try{
            field = parts[3].replaceAll("@","");
        }catch (ArrayIndexOutOfBoundsException e){
            field = "*";
        }


        sql.append("SELECT "+ field + " ");

        sql.append(" FROM "+table+" ");
        sql.append(whereClause);
        sqlRequest = sql.toString();
    }



}
