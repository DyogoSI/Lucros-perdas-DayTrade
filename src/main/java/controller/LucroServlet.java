package controller;

import dao.LucroDao;
import model.Lucro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/salvarLucros")
public class LucroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String dataString = request.getParameter("data");
            String valorString = request.getParameter("valor");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data = sdf.parse(dataString);
            double valor = Double.parseDouble(valorString.replace("R$", "").replace(",", "."));

            Lucro lucro = new Lucro();
            lucro.setData(data);
            lucro.setValor(valor);

            LucroDao lucroDao = new LucroDao();
            lucroDao.salvar(lucro);

            response.sendRedirect("listarLucros"); // Redireciona para a página que lista os lucros

        } catch (ParseException e) {
            throw new ServletException("Erro ao formatar a data", e);
        } catch (NumberFormatException e) {
            throw new ServletException("Erro ao formatar o valor", e);
        } catch (SQLException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LucroDao lucroDao = new LucroDao();
            List<Lucro> lucros = lucroDao.listarTodos();
            request.setAttribute("lucros", lucros);
            request.getRequestDispatcher("listarLucros.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }
}
