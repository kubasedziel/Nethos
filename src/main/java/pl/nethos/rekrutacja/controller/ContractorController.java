package pl.nethos.rekrutacja.controller;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.nethos.rekrutacja.model.account.AccountRepository;
import pl.nethos.rekrutacja.model.contractor.Contractor;
import pl.nethos.rekrutacja.view.AccountView;

public class ContractorController {

    public void expandOrCollapseBankAccounts(AccountRepository accountRepository, Contractor contractor, Button button, VerticalLayout contractorVerticalLayout) {
        if (contractorVerticalLayout.getChildren().count() > 1) {
            contractorVerticalLayout.removeAll();
            contractorVerticalLayout.add(button);
        } else {
            contractorVerticalLayout.add(new AccountView(accountRepository, contractor));
        }
    }
}
