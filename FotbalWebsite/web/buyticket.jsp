<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controllers.RegistrationController"%>
<%@page import="controllers.LoginController"%>
<%@page import="controllers.LogoutController"%>
<%@page import="controllers.Final"%>
<%@page import="controllers.BuyTicketController"%>
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
                                                                <li><a class="btn btn-danger" href="buyticket.jsp">Buy Ticket</a></li>
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
		<div class="content_contact">
			
                    <div class="title2">
			<h1>Buy Ticket:</h1>
                    </div>
                    
                   <div class="form">
			<form method="post" action="Final"> 
				<p>
                                       
					<label for="match">Match</label>
                                          <div class="sel">  
                                            <select name="match">
                                                    <option>FCSB-CFR Cluj, Stadium: National Arena, Date: 10/05/2019 20:45 PM</option>
                                                    <option>Viitorul vs Astra Giurgiu, Stadium: Ovidiu, Date: 11/05/2019 21:00 PM</option>
                                                    <option>Sepsi OSK vs U Craiova, Stadium: Oblemenco, Date: 13/05/2019 20:00 PM</option>
                                            </select>
                                          </div>  
				</p>
                                
                                <p>
                                       
					<label for="area">Area</label> 
                                          <div class="sel">  
                                            <select name="area">
                                                    <option>Peluza I</option>
                                                    <option>Peluza II</option>
                                                    <option>Tribuna I</option>
                                                    <option>Tribuna II</option>
                                                    <option>Tribuna VIP</option>    
                                            </select>  
                                          </div>    
				</p>
                                
                                <p>
					<label for="tickets">Number of tickets</label>
                                        <input class="inpB" type="number" min="0" max="100" name="tickets" required />
                                       
				</p>
                                
                                <p>
                                    <div class="sub">
					<input type="submit" value="Buy" />
                                    </div>
                                </p>
                                
                                          
                        </form>
		    </div>
                    
                    <div class="cola">
                        <img class="imCola3" src="images/cola.png" alt="">
                    </div>
                </div>
	</section>
	
	<section>
		<div class="content2">
			<div class="section">
				<div class="col">
					<a href=" "><h3>NEWS</h3></a>
					
					<img class="im2" src="images/news.jpg" alt="">
				</div>
				
				<div class="col">
					<a href=" "><h3>TABLE</h3></a>
					
					<img class="im2" src="images/table.jpg" alt="">
				</div>
				
				<div class="col">
					<a href=" "><h3>FIXTURES</h3></a>
			
					<img class="im2" src="images/fixtures.jpg" alt="">
				</div>
				
				<div class="col">
					<a href=" "><h3>STATS</h3></a>
					
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
