<%@page import="net.oegupm.wicus.webannotator.Main"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String htmlCode = Main.generateHTML();

%>

<%= htmlCode %>
