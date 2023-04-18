<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--Defining Lists--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%--Parsing XML--%>
<%--Source: https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm--%>
<%@ page import="java.io.File" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.w3c.dom.NodeList" %>
<%@ page import="org.w3c.dom.Element" %>
<%@ page import="javax.servlet.ServletContext" %>


<!DOCTYPE html>
<html>
<head>
    <title>JSP - Assignment 1</title>
</head>
<body>

<h1>
    <%= "JSP Assignment #1" %>
</h1>
<br/>

<%--
    List<String> questions = new ArrayList<>(Arrays.asList("Question1", "Question2"));
    List<String> answers = new ArrayList<>(Arrays.asList("true", "false"));
--%>

<%
    // create questions and answers lists
    List<String> questions = new ArrayList<>();
    List<String> answers = new ArrayList<>();
    // https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
    try {
        // get the absolute path from the relative path
        // https://kodejava.org/how-do-i-get-my-web-application-real-path/
        // String path = getServletContext().getRealPath("/");
        ServletContext context = getServletContext();
        String relativePath = "assignment.xml";
        String absolutePath = context.getRealPath(relativePath);

        // read the XML file into the inputFile object
        File inputFile = new File(absolutePath);

        // create a DocumentBuilder
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        // parse the XML file
        Document doc = dBuilder.parse(inputFile);
        // validate the XML file
        doc.getDocumentElement().normalize();

        // select all the question nodes
        NodeList questionNodes = doc.getElementsByTagName("question");
        NodeList answerNodes = doc.getElementsByTagName("answer");

        // iterate over the question elements
        for (int i = 0; i < questionNodes.getLength(); i++) {

            // store the question
            Element questionElement = (Element) questionNodes.item(i);
            String question = questionElement.getTextContent().trim();
            questions.add(question);

            // store the answer
            Element answerElement = (Element) answerNodes.item(i);
            String answer = answerElement.getTextContent().trim();
            answers.add(answer);
        }

        //out.println("Questions: " + questions);
        //out.println("Answers: " + answers);

    }
    // catch any errors
    catch (Exception e) {
        out.println("Error Parsing XML " + e.getMessage());
        e.printStackTrace();
    }
%>


<form>
    <%
        int i = 0;
        for (String question : questions) {
    %>
    <%=question%>
    <br/><br/>
    True: <input type="radio" name="answer<%=i%>" value="True" required/>
    False: <input type="radio" name="answer<%=i%>" value="False" required/>
    <br/><br/>
    <%
            i++;
        }
    %>
    <input type="submit" value="Submit" name="submit"/>
    <br/><br/>
</form>

<%
    if (request.getParameter("submit") != null) {
        int j = 0;
        int score = 0;
        for (String answer : answers) {
            if (request.getParameter("answer" + j).toLowerCase().equals(answer)) {
                score++;
            }
            j++;
        }
        double grade = (double)score/j*100;
        out.println("Your score is: " + grade + "%");
    }
%>

</body>
</html>