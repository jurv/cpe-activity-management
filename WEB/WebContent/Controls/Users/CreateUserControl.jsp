<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Creation user</title>
</head>
<body>
<f:view>
	<h:form id="create">
	<table>
	  <tr>
	    <td>Nom:</td>
	    <td>
		<!-- <h:commandButton type="submit" id="update" value="ActionUpdate" action="#{CreateUserBean.updateUser}"/> -->
	    </td>
	  </tr>
	</table>
	<h:commandButton type="submit" id="create" value="Create" action="#{CreateUserBean.persist}"/>
	</h:form>
</f:view>
</body>
</html>