package pl.nethos.rekrutacja.controller;

import com.vaadin.flow.component.button.Button;
import pl.nethos.rekrutacja.model.account.Account;
import pl.nethos.rekrutacja.model.account.AccountRepository;
import pl.nethos.rekrutacja.model.contractor.Contractor;
import pl.nip24.client.IBANStatus;
import pl.nip24.client.NIP24Client;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class AccountController {

    public void verifyAccount(Account account, Contractor contractor, AccountRepository accountRepository, Button verifyButton) throws MalformedURLException {
        NIP24Client nip24Client = new NIP24Client();
        IBANStatus ibanStatus = nip24Client.getIBANStatus(contractor.getNip(), account.getNumber());
        account.setVerificationState(Objects.nonNull(ibanStatus) ? ibanStatus.isValid() ? 0 : 1 : 1);
        account.setVerificationDate(new Date(System.currentTimeMillis()));
        accountRepository.update(account);
        updateStatusLabel(verifyButton, account.getVerificationState(), account.getVerificationDate());
    }

    public void updateStatusLabel(Button button, Integer verificationState, Date verificationDate) {
        if (Objects.isNull(verificationState))
            button.setText("Nieokreślony");
        else if (verificationState == 0)
            button.setText("Zweryfikowany");
        else
            button.setText("Błędne konto");
        Optional.ofNullable(verificationDate).ifPresent(date -> button.getElement().setProperty("title", date.toString()));
    }
}
