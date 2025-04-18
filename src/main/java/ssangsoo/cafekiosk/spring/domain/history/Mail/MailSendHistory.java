package ssangsoo.cafekiosk.spring.domain.history.Mail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssangsoo.cafekiosk.spring.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailSendHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String fromEamil;
    private String toEmail;
    private String subject;
    private String content;

    @Builder
    private MailSendHistory(final String fromEamil, final String toEmail, final String subject, final String content) {
        this.fromEamil = fromEamil;
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
    }
}
