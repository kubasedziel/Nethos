package pl.nethos.rekrutacja.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import pl.nethos.rekrutacja.model.account.AccountRepository;
import pl.nethos.rekrutacja.model.contractor.ContractorRepository;

@Route
@PWA(name = "Nethos - Zadanie rekrutacyjne na stanowisko programisty", shortName = "Nethos - Rekrutacja")
public class MainView extends VerticalLayout {

    public MainView(@Autowired ContractorRepository contractorRepository, @Autowired AccountRepository accountRepository) {
        setSizeFull();

        display(contractorRepository, accountRepository);
    }

    private void display(ContractorRepository contractorRepository, AccountRepository accountRepository) {
        add(new ContractorView(contractorRepository, accountRepository));
    }
}
