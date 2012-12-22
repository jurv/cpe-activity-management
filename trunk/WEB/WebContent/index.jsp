
<%@page contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<body>
	<f:view>
		<h1><h:outputText value="Connexion à l'application" /></h1>
		<h:form>
		<table>
			<tr>
				<td>
					<h:outputText value="Identifiant : " />
				</td>
				<td>
					<h:inputText id="loginname" value="" />
				</td>
			</tr>
			<tr>
				<td>
					<h:outputText value="Mot de passe : " />
				</td>
				<td>
					<h:inputSecret id="password" value="" />
				</td>
			</tr>
			<tr>
				<td> </td>
				<td>
					<h:commandButton value="Se connecter" action="welcome"/>
				</td>
			</tr>
		</table>
	</h:form>

	</f:view>
</body>
</html>