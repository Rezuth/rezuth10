<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controllers.RegistrationController"%>
<%@page import="controllers.LoginController"%>
<%@page import="controllers.LogoutController"%>
<%@page import="controllers.BuyTicketController"%>
<%@page import="controllers.ConfirmController"%>
<%@ page session="true" %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>Lab - 6</title>
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/stylesC.css" />
</head>
<body>
	
	<header>
		<div class="header">
			<div class="navigation">
				<div class="title">
					<div class="left">
						<h1>Liga 1 Betano</h1>
					</div>
				</div>
				<div class="right">
					<nav>
							<ul>
                                                                 <li><a class="btn btn-success" href="home.jsp">Home</a></li>
								 <li><a class="btn btn-info" href="register.jsp">Register</a></li>
                                                                 <%if(request.getSession(false) != null &&request.getSession().getAttribute("USER")!=null)
                                                                 {%>
                                                                 <li><a class="btn btn-warning" href="LogoutController">Logout</a></li>   
                                                                 <li><a class="btn btn-primary" href="PurchaseController">My Account</a></li> 
                                                                 <%}else
                                                                 {%>
                                                                 <li><a class="btn btn-warning" href="login.jsp">Login</a></li>
                                                                 <%}%>                    
                                                                 <li><a class="btn btn-danger" href="BuyTicketController">Buy Ticket</a></li>
                                                                 <%if(request.getSession(false) != null &&request.getSession().getAttribute("USER")!=null)
                                                                 {%>
                                                                 <li><p>Hello <%=request.getSession().getAttribute("USER")%></p></li>
                                                                 <%}else
                                                                 {%>
                                                                 <li><p>Hello GUEST</p></li>
                                                                 <%}%>  		
							</ul>
					</nav>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</header>
	
	<header>
		<div class="header2">
			<div class="navigation2">
				<div class="left">
					<nav>
							<ul>
								<li>This site is sponsored by Coca Cola</li>
								
							</ul>
					</nav>
				</div>
				<div class="title2">
					<div class="right">
						<h4>FOLLOW US!</h4>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</header>
	

	<section>
		<div class="content">
			<div class="picture">
                            <h1 style="color: #ffffff;">4 players out from FCSB!</h1>
				<img class="im" src="images/becali.jpg" alt="">
                                <br>
                                <br>
                                <br>
                                <br>
                                <div class="textXD">Gigi Becali started the revolution at FCSB. The red-blue owner announced the first 4 players to leave the vice-champion. Filipe Teixeira,<br> Raul Rusescu, Adrian Stoian and Florentin Matei are no longer part of the sponsor's plans. Former Crotone player can reach Craiova or CFR.<br>
                                    "Teixeira is not left, he's finished the contract, it's going to be another stage, Rusescu thanks him, terminates his contract, he's a boy of<br> character, he's finished his contract, he's a good boy.<br>
                                    Matthew and Stoian are leaving. Stoian takes either CFR or Craiova, I think. Matei does not think he wants to stay at Steaua to train<br> on the heights. I do not think he will sign elsewhere. He took 50,000, he also took 15,000 per month. I'm not sorry for the money I gave."<br>Becali told Pro X.
                               
                                   
                                   
			</div>
                                <br>
                                <br>
                                <br>
                        
                              
                                <a href="news.jsp" style="font-size:160%; color: #77ff21;" class="ORR">1</a>
                                <a href="2.jsp" style="font-size:160%; color: #77ff21;" class="ORR">2</a>
                                <a href="3.jsp" style="font-size:160%; color: #77ff21;" class="ORR">3</a>
                              
                               
                </div>
	</section>
	
	<section>
		<div class="content2">
			<div class="section">
				<div class="col">
					<a href="news.jsp"><h3>NEWS</h3></a>
					
					<img class="im2" src="images/news.jpg" alt="">
				</div>
				
				<div class="col">
					<a href="table.jsp"><h3>TABLE</h3></a>
					
					<img class="im2" src="images/table.jpg" alt="">
				</div>
				
				<div class="col">
					<a href="fixtures.jsp"><h3>FIXTURES</h3></a>
			
					<img class="im2" src="images/fixtures.jpg" alt="">
				</div>
				
				<div class="col">
					<a href="stats.jsp"><h3>STATS</h3></a>
					
					<img class="im2" src="images/stats.jpg" alt="">
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</section>

	<footer>
		<div class="footer">
			<div class="last_content">
				
				<div class="ftext">
					<a href="https://twitter.com/liga1romania"><img class="im3"src="images/twitter.png" alt=""></a>
					<a href="https://www.facebook.com/lpf.ro/"><img class="im3"src="images/facebook.png" alt=""></a>
					<a href="https://www.instagram.com/ligaprofesionistadefotbal/"><img class="im3"src="images/instagram.png" alt=""></a>
				</div>
							
			</div>
			<div class="clear"></div>
		</div>
	</footer>
	
	

	
</body>
</html>