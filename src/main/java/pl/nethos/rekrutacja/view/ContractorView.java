package pl.nethos.rekrutacja.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.nethos.rekrutacja.model.account.AccountRepository;
import pl.nethos.rekrutacja.model.contractor.ContractorRepository;
import pl.nethos.rekrutacja.controller.ContractorController;

class ContractorView extends VerticalLayout {

    private static final String NAME_LABEL_WIDTH = "440px";
    private static final String NIP_LABEL_WIDTH = "150px";
    private static final String BUTTON_WIDTH = "600px";

    private ContractorController contractorController = new ContractorController();

    ContractorView(ContractorRepository contractorRepository, AccountRepository accountRepository) {
        display(createHead(), contractorRepository, accountRepository);
    }

    private HorizontalLayout createHead() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(createNameLabel("Nazwa kontrahenta"), createNipLabel("NIP"));
        horizontalLayout.getStyle().set("margin-bottom", "15px");
        return horizontalLayout;
    }

    private void display(HorizontalLayout head, ContractorRepository contractorRepository, AccountRepository accountRepository) {
        removeAll();
        add(head);
        contractorRepository.all().forEach(contractor -> {
            Button button = new Button();
            button.getStyle().set("background-color", "white");
            button.getStyle().set("color", "black");
            button.getStyle().set("border", "none");
            button.getStyle().set("margin-top", "0px");
            button.setMinWidth(BUTTON_WIDTH);
            button.setMaxWidth(BUTTON_WIDTH);
            VerticalLayout contractorVerticalLayout = new VerticalLayout(button);
            contractorVerticalLayout.getStyle().set("margin-top", "0px");
            contractorVerticalLayout.getStyle().set("padding-left", "0px");
            contractorVerticalLayout.getStyle().set("padding-right", "0px");
            contractorVerticalLayout.getStyle().set("padding-top", "5px");
            contractorVerticalLayout.getStyle().set("padding-bottom", "5px");
            HorizontalLayout icon = new HorizontalLayout(createNameLabel(contractor.getName()), createNipLabel(contractor.getNip()));
            icon.setMinWidth(BUTTON_WIDTH);
            icon.setMaxWidth(BUTTON_WIDTH);
            icon.getStyle().set("padding-left", "0px");
            icon.getStyle().set("padding-right", "0px");
            icon.getStyle().set("padding-top", "5px");
            icon.getStyle().set("padding-bottom", "5px");
            button.setIcon(icon);
            button.addClickListener(event -> contractorController
                    .expandOrCollapseBankAccounts(accountRepository, contractor, button, contractorVerticalLayout));
            add(contractorVerticalLayout);
        });
    }

    private Label createNameLabel(String name) {
        return createLabel(name, NAME_LABEL_WIDTH);
    }

    private Label createNipLabel(String nip) {
        return createLabel(nip, NIP_LABEL_WIDTH);
    }

    private Label createLabel(String text, String size) {
        Label label = new Label(text);
        label.setMinWidth(size);
        label.setMaxWidth(size);
        label.getStyle().set("text-align", "left");
        return label;
    }
}


