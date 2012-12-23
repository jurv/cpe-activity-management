
	<%@ include file="Template/header.jsp" %>
	<body id="login">
	<f:view>
		<header>
			<h:outputText value="Connexion à l'application" />
		</header>
		<section id="content">
			<h:form id="loginform">
				<fieldset>
					<section>
						<label for="loginname"> Identifiant </label>
						<div>
							<h:inputText id="loginname" value="" />
						</div>
					</section>
					<section>
						<label for="password"> Mot de passe </label>
						<div>
							<h:inputSecret id="password" value="" />
						</div>
					</section>
					<section>
						<div>
							<h:commandButton value="Se connecter" action="connection"/>
						</div>
					</section>
				</fieldset>
			</h:form>

		</section>

	</f:view>
	<footer> CPE Activity Management all right reserved </footer>
	</body>
	
	<%@ include file="Template/footer.jsp" %>
