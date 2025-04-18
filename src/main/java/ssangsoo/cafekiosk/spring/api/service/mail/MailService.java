package ssangsoo.cafekiosk.spring.api.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssangsoo.cafekiosk.spring.client.mail.MailSendClient;
import ssangsoo.cafekiosk.spring.domain.history.Mail.MailSendHistory;
import ssangsoo.cafekiosk.spring.domain.history.Mail.MailSendHistoryRepository;

@RequiredArgsConstructor
@Service
public class MailService {

    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(final String fromEamil, final String toEmail, final String subject, final String content) {
        boolean result = mailSendClient.sendEmail(fromEamil, toEmail, subject, content);
        if(result) {
            mailSendHistoryRepository.save(MailSendHistory.builder()
                    .fromEamil(fromEamil)
                    .toEmail(toEmail)
                    .subject(subject)
                    .content(content)
                    .build()
            );
            return true;
        }

        return false;
    }
}
