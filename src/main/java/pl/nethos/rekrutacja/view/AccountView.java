package pl.nethos.rekrutacja.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.nethos.rekrutacja.model.account.Account;
import pl.nethos.rekrutacja.model.account.AccountRepository;
import pl.nethos.rekrutacja.model.contractor.Contractor;
import pl.nethos.rekrutacja.controller.AccountController;
import pl.nethos.rekrutacja.utils.StringHelper;

import java.net.MalformedURLException;
import java.util.List;

public class AccountView extends VerticalLayout {

    private static final String NUMBER_LABEL_WIDTH = "400px";
    private static final String STATE_LABEL_WIDTH = "100px";

    private AccountController accountController = new AccountController();

    public AccountView(AccountRepository accountRepository, Contractor contractor) {
        display(createHead(), accountRepository.findByContractor(contractor), contractor, accountRepository);
    }

    private HorizontalLayout createHead() {
        return new HorizontalLayout(createNumberLabel("Numer konta"), createStateLabel("Aktywne"),
                createStateLabel("Domyślne"), createStateLabel("Wirtualne"), createStateLabel("Status"));
    }

    private void display(HorizontalLayout head, List<Account> accounts, Contractor contractor, AccountRepository accountRepository) {
        removeAll();
        add(head);
        accounts.forEach(account -> {
            Button verifyButton = new Button();
            accountController.updateStatusLabel(verifyButton, account.getVerificationState(), account.getVerificationDate());
            verifyButton.addClickListener(event -> {
                try {
                    accountController.verifyAccount(account, contractor, accountRepository, verifyButton);
                } catch (MalformedURLException e) {
                    System.out.println(e.getMessage());
                }

            });
            add(new HorizontalLayout(createNumberLabel(
                    StringHelper.formatAccountNumberIfValid(account.getNumber())), createStateLabel(account.getActive()),
                    createStateLabel(account.getDefaultAccount()), createStateLabel(account.getVirtual()),
                    verifyButton));
        });
        getStyle().set("margin-top", "0px");
    }

    private Label createNumberLabel(String number) {
        return createLabel(number, NUMBER_LABEL_WIDTH);
    }

    private Label createStateLabel(String state) {
        return createLabel(state, STATE_LABEL_WIDTH);
    }

    private Label createStateLabel(Integer state) {
        return createLabel(state.toString(), STATE_LABEL_WIDTH);
    }

    private Label createLabel(String text, String size) {
        Label label = new Label(text);
        label.setMinWidth(size);
        label.setMaxWidth(size);
        label.getStyle().set("text-align", "left");
        return label;
    }
}
