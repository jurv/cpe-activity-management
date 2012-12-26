
	<%@ include file="../Template/header.jsp" %>
	<body>
	<f:view>
		<div id="pageoptions">
        	<ul>
        		<li> <h:outputText value="#{DashboardBean.currentUser.usrFirstname}" /> <a href="">Se d&eacute;connecter</a></li>
        	</ul>
		</div>
		<%@ include file="../Template/topmenu.jsp" %>
		<%@ include file="../Template/menu.jsp" %>
		
		<section id="content">
        	<div id="main" class="control_path"></div>
		</section>
		
	</f:view>
	<footer> CPE Activity Management all right reserved </footer>
	</body>
	
	<%@ include file="../Template/footer.jsp" %>
