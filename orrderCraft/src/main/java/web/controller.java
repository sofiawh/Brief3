package web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
//import java.util.List;
import java.util.List;

import com.model.Client;
import com.model.Commande;
import com.model.Article;
import com.model.ArticleCommande;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.ClientDaoImpl;
import dao.CommandeDaoImpl;
import dao.IArticleCommandeDao;
import dao.IClientDao;
import dao.ICommandeDao;
import dao.IArticleDao;
import dao.ArticleCommandeDaoImpl;
import dao.ArticleDaoImpl;

//import net.javaguides.todoapp.dao.TodoDao;
//import net.javaguides.todoapp.dao.TodoDaoImpl;
//import net.javaguides.todoapp.model.Todo;





/**
 * Servlet implementation class controller
 */
@WebServlet(name="cs",urlPatterns= {"*.php"})
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IArticleDao metier;
    private IClientDao metierC;
    private ICommandeDao metierCmd;
	private IArticleCommandeDao metierArtCmd;
    
    public controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
			metier = new ArticleDaoImpl();
			metierC = new ClientDaoImpl();
			metierCmd = new CommandeDaoImpl();
			metierArtCmd = new ArticleCommandeDaoImpl();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String path=request.getServletPath();
			if(path.equals("/index.php")) {
				request.getRequestDispatcher("articles.jsp").forward(request, response);
			}
			else if(path.equals("/commandesEnCours.php")) {
				// response.sendRedirect("chercherA.php?motCle=");
				response.sendRedirect("chercherCommandeEnC.php");
				//request.getRequestDispatcher("commandesEnCours.jsp").forward(request, response);
			}
			else if(path.equals("/chercherA.php")) {
				String motCle=request.getParameter("motCle");
				//System.out.println("motCle***"+motCle);
				//if(motCle==null) {motCle="";}
				ArticleModel model=new ArticleModel();
				model.setMotCle(motCle);
				List<Article> articles=metier.articlesParMC("%"+motCle+"%");
				model.setArticles(articles);
				request.setAttribute("model", model);
				request.getRequestDispatcher("articles.jsp").forward(request, response);
			}
			else if(path.equals("/Saisie.php")) {
				request.setAttribute("article", new Article());
				request.getRequestDispatcher("SaisieArticle.jsp").forward(request, response);
			}
			else if(path.equals("/SaveArticle.php")&&(request.getMethod().equals("POST"))) {
				String des=request.getParameter("designation");
				double prix=Double.parseDouble(request.getParameter("prix"));
				int quantite=Integer.parseInt(request.getParameter("quantite"));
				Article p=metier.save(new Article(des, prix, quantite));
				request.setAttribute("article", p);
				request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
				}
			else if(path.equals("/Supprimer.php")) {
			    Long id=Long.parseLong(request.getParameter("id"));
			    metier.deleteArticle(id);
			     response.sendRedirect("chercherA.php?motCle=");
			}
			else if(path.equals("/Edit.php")) {
			    Long id=Long.parseLong(request.getParameter("id"));
			    Article p=metier.getArticle(id);
			    request.setAttribute("article", p);
			    request.getRequestDispatcher("EditArticle.jsp").forward(request, response);
				}
			else if(path.equals("/UpdateArticle.php")&&(request.getMethod().equals("POST"))) {
				Long id=Long.parseLong(request.getParameter("id"));
				String des=request.getParameter("designation");
				double prix=Double.parseDouble(request.getParameter("prix"));
				int quantite=Integer.parseInt(request.getParameter("quantite"));
				Article p=new Article(des, prix, quantite);
				p.setId(id);
				metier.update(p);
				request.setAttribute("article", p);
				request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
				}
			else if(path.equals("/SaisieClient.php")) {
				request.setAttribute("client", new Client());
				request.getRequestDispatcher("SaisieClient.jsp").forward(request, response);
			}
			else if(path.equals("/SaveClient.php")&&(request.getMethod().equals("POST"))) {
				String nom=request.getParameter("nom");
				String email=request.getParameter("email");
				String adresse=request.getParameter("adresse");
				String tel=request.getParameter("tel");
				Client p=metierC.save(new Client(nom, email, adresse, tel));
				request.setAttribute("client", p);
				request.getRequestDispatcher("ConfirmationClient.jsp").forward(request, response);
				}
			else if(path.equals("/HomeClient.php")) {
				request.getRequestDispatcher("clients.jsp").forward(request, response);
			}
			else if(path.equals("/chercher.php")) {
				String motCle=request.getParameter("motCle");
				ClientModel model=new ClientModel();
				model.setMotCle(motCle);
				List<Client> clients=metierC.clientsParMC("%"+motCle+"%");
				model.setClients(clients);
				request.setAttribute("model", model);
				request.getRequestDispatcher("clients.jsp").forward(request, response);
			}
			else if(path.equals("/SupprimerC.php")) {
			    Long id=Long.parseLong(request.getParameter("id"));
			    metierC.deleteClient(id);
			    response.sendRedirect("chercher.php?motCle=");
			}
			else if(path.equals("/UpdateClient.php")&&(request.getMethod().equals("POST"))) {
				Long id=Long.parseLong(request.getParameter("id"));
				String nom=request.getParameter("nom");
				String email=request.getParameter("email");
				String adresse=request.getParameter("adresse");
				String tel=request.getParameter("tel");
				Client c=new Client(nom, email, adresse, tel);
				c.setId(id);
				metierC.update(c);
				request.setAttribute("client", c);
				request.getRequestDispatcher("ConfirmationClient.jsp").forward(request, response);
			}
			else if(path.equals("/EditC.php")) {
			    Long id=Long.parseLong(request.getParameter("id"));
			    Client c=metierC.getClient(id);
			    request.setAttribute("client", c);
			    request.getRequestDispatcher("EditClient.jsp").forward(request, response);
			}
			else if(path.equals("/articleCommandes.php")) {
				request.getRequestDispatcher("articleCommandes.jsp").forward(request, response);
			}
			else if(path.equals("/PasserCommande.php")) {
			    Long id=Long.parseLong(request.getParameter("id"));
			    Client c=metierC.getClient(id);			    
		        HttpSession session = request.getSession();		       
		        Client client = (Client) session.getAttribute("client");
		        if (client == null) {
		            client = c;
		        }
		        session.setAttribute("client", client);
				request.getRequestDispatcher("SaisieCommande.jsp").forward(request, response);
 	}
			else if(path.equals("/SaveCommande.php")&&(request.getMethod().equals("POST"))) {
				String num=request.getParameter("Numero");
				String dateP=request.getParameter("date");
				boolean status = Boolean.parseBoolean(request.getParameter("Status"));
				System.out.println("Status***"+status);
				Date date=new Date();
				if (dateP != null && !dateP.isEmpty()) {
				    try {
				        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				         date = (Date) dateFormat.parse(dateP);
				        } catch (ParseException e) {
				        e.printStackTrace(); 
				    }
				} 
				Commande c=new Commande(num, status, date);
				HttpSession session = request.getSession();
			    Client client = (Client) session.getAttribute("client");
			    System.out.println("controller client id ***"+client.getId());			
				c.setClient(client);
				Commande c2=metierCmd.save(c);				  
			    Commande commande = (Commande) session.getAttribute("commande");
			        if (commande == null) {
			        	commande = c2;
			        }
				session.setAttribute("commande", c2);				
				request.getRequestDispatcher("SaisieArticleCommande.jsp").forward(request, response);	
				}
			else if(path.equals("/SaveArticleCommande.php")&&(request.getMethod().equals("POST"))) {
				long articleId=Long.parseLong(request.getParameter("articleId"));				
				int quantite=Integer.parseInt(request.getParameter("quantite"));				
				HttpSession session = request.getSession();
				Commande commande = (Commande) session.getAttribute("commande");		
				 Article article=metier.getArticle(articleId);
				 System.out.println("article stock;**"+article.getQuantite());
				 ArticleCommande artCmd= new ArticleCommande();	
				 article.setQuantite(article.getQuantite()-quantite);
				 metier.update(article);
				 artCmd.setQuantite(quantite);				 
				 artCmd.setArticle(article);
				 System.out.println("artCmd.getArticle().getQuantite()***"+artCmd.getArticle().getQuantite());
				 artCmd.setCommande(commande);
				 artCmd.setSousTotal(artCmd.calculerSousTotal()); 			 
				 ArticleCommande arc=metierArtCmd.save(artCmd);				 
				 ArticleCommande articleCommande = (ArticleCommande) session.getAttribute("articleCommande");
			        if (articleCommande == null) {
			        	articleCommande = arc;
			        }				
				session.setAttribute("artCommande", arc);	 
				ArticleCommandeModel model=new ArticleCommandeModel();
				Long motCle=commande.getId();
				System.out.println("motCle=commande.getId();***"+motCle);
				model.setMotCle(motCle);
				List<ArticleCommande> articleCommandes=metierArtCmd.articleCommandesParMC(motCle);
				model.setArticleCommandes(articleCommandes);
				for (ArticleCommande articleCommande2 : articleCommandes) {
					System.out.println("articleCommande2***"+articleCommande2.getQuantite()+" * "+articleCommande2.getSousTotal());
					System.out.println("articleCommande2***"+articleCommande2.calculerSousTotal());
				}
		        double t=model.calculerTotal();
		        Double total = (Double) session.getAttribute("total");
                    if (total == null) {
        	                   total = t;
                               }				
	            session.setAttribute("total", t);
				request.setAttribute("model", model);
				request.getRequestDispatcher("SaisieArticleCommande.jsp").forward(request, response);			
			}
			else if(path.equals("/chercherArtCmd.php")) {				
				String motCle=request.getParameter("motCle");
				ClientModel model=new ClientModel();
				model.setMotCle(motCle);
				List<Client> clients=metierC.clientsParMC("%"+motCle+"%");
				model.setClients(clients);
				request.setAttribute("model", model);
				request.getRequestDispatcher("articleCommandes.jsp").forward(request, response);
			}
			else if(path.equals("/chercherCommandeEnC.php")) {
				boolean motCle=false;
				CommandeModel model=new CommandeModel();
				model.setMotCle(motCle);
				List<Commande> commandes=metierCmd.commandesParMC(motCle);
				model.setCommandes(commandes);
				request.setAttribute("model", model);
				request.getRequestDispatcher("commandesEnCours.jsp").forward(request, response);
			}
			else if(path.equals("/EditCmd.php")) {
			    Long id=Long.parseLong(request.getParameter("id"));
			    Commande c=metierCmd.getCommande(id);
			    c.setStatus(true);
			    metierCmd.update(c);
			    List<ArticleCommande> artCmd=metierArtCmd.articleCommandesParMC(id);
			    c.setArticlesCommandes(artCmd);			    
			    //request.getRequestDispatcher("commandesEnCours.jsp").forward(request, response);
			    response.sendRedirect("chercherCommandeEnC.php");
			}
			else {
				response.sendError(response.SC_NOT_FOUND);
			}
			
			/*
			request.getRequestDispatcher("articles.jsp").forward(request, response);;
			String action = request.getServletPath();

			try {
				switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertTodo(request, response);
					break;
				case "/delete":
					deleteTodo(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateTodo(request, response);
					break;
				default:
					listTodo(request, response);
					break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			}*/
		}
/*
		private void listTodo(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			//List<Todo> listTodo = todoDAO.selectAllTodos();
		//	request.setAttribute("listTodo", listTodo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
			dispatcher.forward(request, response);
		}

		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ligneCommande.jsp");
			dispatcher.forward(request, response);
		}

		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			//int id = Integer.parseInt(request.getParameter("id"));
			//Todo existingTodo = todoDAO.selectTodo(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
			//request.setAttribute("todo", existingTodo);
			dispatcher.forward(request, response);
		}

		private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			String title = request.getParameter("title");
			String username = request.getParameter("username");
			String description = request.getParameter("description");
			boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
			//Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
			//todoDAO.insertTodo(newTodo);
			response.sendRedirect("list");
		}

		private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));

			String title = request.getParameter("title");
			String username = request.getParameter("username");
			String description = request.getParameter("description");
			LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));

			boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
			//Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);

			//todoDAO.updateTodo(updateTodo);

			response.sendRedirect("list");
		}

		private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			//todoDAO.deleteTodo(id);
			response.sendRedirect("list");
		}*/
		

}
