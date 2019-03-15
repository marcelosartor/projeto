package relatorioskillusportal.repositorio.helper;

import java.math.BigDecimal;
import java.time.MonthDay;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaContext;

import relatorioskillusportal.dto.VendaCiclo;
import relatorioskillusportal.model.RelatorioPortal;

public class VendasImpl implements VendasQueries{

	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VendaCiclo> totalPorCiclo(JpaContext context) {
		 manager = context.getEntityManagerByManagedType(RelatorioPortal.class);
		 return manager.createNamedQuery("Vendas.totalPorCiclo").getResultList();
	}

	//@Override
	//public BigDecimal totalVendaPorCiclo() {
		//Optional<BigDecimal> optional = Optional.ofNullable(manager.createNamedQuery("Vendas.totalPorCiclo", BigDecimal.class)
			//	    .setParameter("Vendas.totalPorCiclo", MonthDay.now().getMonthValue()).getSingleResult());
		//return optional.orElse(BigDecimal.ZERO);
	//}

}