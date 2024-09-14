// package gov.iti.jets.listeners;


// import jakarta.servlet.ServletContextEvent;
// import jakarta.servlet.ServletContextListener;
// import jakarta.servlet.annotation.WebListener;
// import jakarta.persistence.EntityManagerFactory;
// import jakarta.persistence.Persistence;
// import jakarta.servlet.ServletContext;



// /* ============================ ==================================================== */
// /* DBContextlistener  That is called when the application is deployed                */
// /* ============================ ==================================================== */
// @WebListener
// public class DBContextlistener implements ServletContextListener {

//     @Override
//     public void contextInitialized(ServletContextEvent servletContextEvent) {
//         System.out.println("Servlet Context Initialized");

//         ServletContext contextObject = servletContextEvent.getServletContext();

//         try {
//             EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercePU");

//             contextObject.setAttribute("emf", emf);            

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }


//     @Override
//     public void contextDestroyed(ServletContextEvent servletContextEvent) {
//         System.out.println("Servlet Context Destroyed");

//         EntityManagerFactory emf = (EntityManagerFactory) servletContextEvent.getServletContext().getAttribute("emf");

//         if (emf != null) {
//             emf.close(); // Close the factory itself
//         }
//     }


// }


// @WebListener
// public class DBContextlistener implements ServletContextListener {

//     @Override
//     public void contextInitialized(ServletContextEvent servletContextEvent) {
//         System.out.println("Servlet Context Initialized");

//         ServletContext contextObject = servletContextEvent.getServletContext();

//         try {
//             EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab6part1");
//             System.out.println("EntityManagerFactory created: " + emf);
//             contextObject.setAttribute("emf", emf);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }

//     @Override
//     public void contextDestroyed(ServletContextEvent servletContextEvent) {
//         System.out.println("Servlet Context Destroyed");

//         EntityManagerFactory emf = (EntityManagerFactory) servletContextEvent.getServletContext().getAttribute("emf");

//         if (emf != null) {
//             emf.close(); // Close the factory itself
//         }
//     }
// }
