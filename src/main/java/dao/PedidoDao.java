package dao;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {


    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido Pedido) {

        this.em.persist(Pedido);

    }

    public BigDecimal valorTotalVendido() {

        String jpql = "SELECT SUM(p.valorTotal) from Pedido p"; //função de agregação
        return em.createQuery(jpql, BigDecimal.class).getSingleResult(); //traz apenas um unico resultado;
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas() { //array de objeto


        String jpql = "select new br.com.alura.loja.vo.RelatorioDeVendasVo (" +
                " produto.nome ," +
                "SUM(item.quantidade)," +
                " MAX(pedido.data)) " +
                " from Pedido pedido join pedido.itens item " +
                "join item.produto produto" +
                " group by produto.nome" +
                "  order by item.quantidade desc ";

        return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }

    public Pedido buscarPedidoComCliente(Long id) {

        return em.createQuery(" SELECT  p from Pedido p join Fetch p.cliente WHERE p.id = :id",Pedido.class)
                .setParameter("id", id)  //join fech faz com que faça a configuaração fique eager
                .getSingleResult();



    }

}

