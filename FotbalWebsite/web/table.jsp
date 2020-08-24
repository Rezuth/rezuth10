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
        <link rel="stylesheet" href="table.css" /> 
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
                                                                 <li><a class="btn btn-primary" href="LogoutController">My Account</a></li> 
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
            
            
               
			
                               

                        <div class="ptable">
                            <h1 class="headin">Table</h1>
					<table>
						<tr class="colXD">
							<th>#</th>
							<th>Team</th>
							<th>GP</th>
							<th>W</th>
							<th>D</th>
							<th>L</th>
							<th>GD</th>
							<th>PTS</th>
						</tr>
						<tr class="wpos">
							<td>1</td>
							<td>CFR Cluj</td>
							<td>2</td>
							<td>2</td>
							<td>0</td>
							<td>0</td>
							<td>5</td>
							<td>50</td>
						</tr>
						<tr class="wpos">
							<td>2</td>
							<td>FCSB</td>
							<td>2</td>
							<td>2</td>
							<td>0</td>
							<td>0</td>
							<td>4</td>
							<td>48</td>
						</tr>
						<tr class="wpos">
							<td>3</td>
							<td>FC Viitorul</td>
							<td>2</td>
							<td>1</td>
							<td>1</td>
							<td>0</td>
							<td>4</td>
							<td>45</td>
						</tr>
						<tr class="wpos">
							<td>4</td>
							<td>U Craiova</td>
							<td>2</td>
							<td>1</td>
							<td>1</td>
							<td>0</td>
							<td>1</td>
							<td>42</td>
						</tr>
						<tr class="wpos">
							<td>5</td>
							<td>FC Astra Giurgiu</td>
							<td>2</td>
							<td>1</td>
							<td>0</td>
							<td>1</td>
							<td>0</td>
							<td>37</td>
						</tr>
						<tr class="wpos">
							<td>6</td>
							<td>FC Sepsi OSK</td>
							<td>3</td>
							<td>1</td>
							<td>0</td>
							<td>2</td>
							<td>-1</td>
							<td>35</td>
						</tr>
						<tr class="pos">
							<td>7</td>
							<td>FC Botosani</td>
							<td>2</td>
							<td>1</td>
							<td>0</td>
							<td>1</td>
							<td>-2</td>
							<td>30</td>
						</tr>
						<tr class="pos">
							<td>8</td>
							<td>CSM Poli Iasi</td>
							<td>1</td>
							<td>0</td>
							<td>1</td>
							<td>0</td>
							<td>0</td>
							<td>28</td>
						</tr>
						<tr class="pos">
							<td>9</td>
							<td>Gaz Metan Medias</td>
							<td>2</td>
							<td>0</td>
							<td>1</td>
							<td>1</td>
							<td>-1</td>
							<td>27</td>
						</tr>
						<tr class="pos">
							<td>10</td>
							<td>Dunarea Calarasi</td>
							<td>2</td>
							<td>0</td>
							<td>0</td>
							<td>2</td>
							<td>-4</td>
							<td>27</td>
						</tr>
						<tr class="pos">
							<td>11</td>
							<td>FC Dinamo Bucuresti</td>
							<td>2</td>
							<td>0</td>
							<td>0</td>
							<td>2</td>
							<td>-5</td>
							<td>26</td>
						</tr>
                                                
                                                <tr class="pos">
							<td>12</td>
							<td>AFC Hermannstadt</td>
							<td>2</td>
							<td>0</td>
							<td>0</td>
							<td>2</td>
							<td>-5</td>
							<td>24</td>
						</tr>
                                                
                                                <tr class="pos">
							<td>13</td>
							<td>FC Voluntari</td>
							<td>2</td>
							<td>0</td>
							<td>0</td>
							<td>2</td>
							<td>-5</td>
							<td>20</td>
						</tr>
                                                
                                                   <tr class="pos">
							<td>14</td>
							<td>CS Concordia Chiajna</td>
							<td>2</td>
							<td>0</td>
							<td>0</td>
							<td>2</td>
							<td>-5</td>
							<td>15</td>
						</tr>
					</table>
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