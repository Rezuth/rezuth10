<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controllers.RegistrationController"%>
<%@page import="controllers.LoginController"%>
<%@page import="controllers.LogoutController"%>
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
                                                                 
                                                                <%}else
                                                                {%>
                                                                <li><a class="btn btn-warning" href="login.jsp">Login</a></li>
                                                                <%}%>                    
                                                                <li><a class="btn btn-danger" href="BuyTicketController">Buy Ticket</a></li>
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
			<h1>Login:</h1>
                    </div>
                    
                   <div class="form">
			<form method="post" action="LoginController">
				<p>
					<label for="username">Username</label>
					<input class="inp" type="text" name="username" required />	
				</p>
                                
                                <p>
					<label for="password">Password</label>
					<input class="inp" type="text" name="password" required />
				</p>
                                
                                <p>
                                            <div class="sub">
						<input type="submit" value="Login" />
                                            </div>
                                </p>
                                
                                            <div style="color: #FF0000;">${ERRORS}</div>
                        </form>
		    </div>
                    
                    <div class="cola">
                        <img class="imCola2" src="images/cola.png" alt="">
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
