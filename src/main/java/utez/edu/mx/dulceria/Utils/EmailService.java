package utez.edu.mx.dulceria.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import utez.edu.mx.dulceria.user.DTO.UserDTO;
import utez.edu.mx.dulceria.user.model.User;
import utez.edu.mx.dulceria.user.service.UserService;

import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;

@Component

public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    public boolean sendEmail (UserDTO userDTO){
        User user = userService.getByUsername(userDTO.getUsername()).get();
        try {
            user.setCode(code());
            userService.save(user);
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(user.getUsername());
            helper.setSubject("Dulceria | Nuevo mensaje");
            helper.setText(template(user),true);
            mailSender.send(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String template(User userDTO) {

        String htmlEmail = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" " +
                "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\" width: 100%; font-family: helvetica, 'helvetica neue', arial, verdana, sans-serif; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; padding: 0; margin: 0; \" > <head> <meta charset=\"UTF-8\" /> <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\" /> <meta name=\"x-apple-disable-message-reformatting\" /> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /> <meta content=\"telephone=no\" name=\"format-detection\" /> <title></title> <link href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i\" rel=\"stylesheet\" /> <style type=\"text/css\"> #outlook a { padding: 0; } .ExternalClass { width: 100%; } .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div { line-height: 100%; } .es-button { mso-style-priority: 100 !important; text-decoration: none !important; transition: all 100ms ease-in; } a[x-apple-data-detectors] { color: inherit !important; text-decoration: none !important; font-size: inherit !important; font-family: inherit !important; font-weight: inherit !important; line-height: inherit !important; } .es-button:hover { background: #049474  !important; border-color: #555555 !important; } .es-desk-hidden { display: none; float: left; overflow: hidden; width: 0; max-height: 0; line-height: 0; mso-hide: all; } [data-ogsb] .es-button { border-width: 0 !important; padding: 15px 30px 15px 30px !important; } @media only screen and (max-width: 600px) { p, ul li, ol li, a { line-height: 150% !important; } h1, h2, h3, h1 a, h2 a, h3 a { line-height: 120% !important; } h1 { font-size: 30px !important; text-align: center; } h2 { font-size: 26px !important; text-align: left; } h3 { font-size: 20px !important; text-align: left; } h1 a { text-align: center; } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size: 30px !important; } h2 a { text-align: left; } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size: 20px !important; } h3 a { text-align: left; } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size: 20px !important; } .es-menu td a { font-size: 16px !important; } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size: 16px !important; } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size: 17px !important; } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size: 17px !important; } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size: 12px !important; } *[class=\"gmail-fix\"] { display: none !important; } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align: center !important; } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align: right !important; } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align: left !important; } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display: inline !important; } .es-button-border { display: inline-block !important; } a.es-button, button.es-button { font-size: 14px !important; display: inline-block !important; border-width: 15px 25px 15px 25px !important; } .es-btn-fw { border-width: 10px 0px !important; text-align: center !important; } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width: 100% !important; } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width: 100% !important; max-width: 600px !important; } .es-adapt-td { display: block !important; width: 100% !important; } .adapt-img { width: 100% !important; height: auto !important; } .es-m-p0 { padding: 0px !important; } .es-m-p0r { padding-right: 0px !important; } .es-m-p0l { padding-left: 0px !important; } .es-m-p0t { padding-top: 0px !important; } .es-m-p0b { padding-bottom: 0 !important; } .es-m-p20b { padding-bottom: 20px !important; } .es-mobile-hidden, .es-hidden { display: none !important; } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width: auto !important; overflow: visible !important; float: none !important; max-height: inherit !important; line-height: inherit !important; } tr.es-desk-hidden { display: table-row !important; } table.es-desk-hidden { display: table !important; } td.es-desk-menu-hidden { display: table-cell !important; } .es-menu td { width: 1% !important; } table.es-table-not-adapt, .esd-block-html table { width: auto !important; } table.es-social { display: inline-block !important; } table.es-social td { display: inline-block !important; } } </style> </head> <body style=\" width: 100%; font-family: helvetica, 'helvetica neue', arial, verdana, sans-serif; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; padding: 0; margin: 0; background-color: #f1f1f1; \" > <div class=\"es-wrapper-color\"> <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\" border-collapse: collapse; border-spacing: 0px; padding: 0; margin: 0; width: 100%; height: 100%; background-repeat: repeat; background-position: center top; \" > <tr style=\"border-collapse: collapse\"> <td valign=\"top\" style=\"padding: 0; margin: 0\"> <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" style=\" border-collapse: collapse; border-spacing: 0px; table-layout: fixed !important; width: 100%; \" > <tr style=\"border-collapse: collapse\"> <td align=\"center\" style=\"padding: 0; margin: 0\"> <table class=\"es-content-body\" style=\" border-collapse: collapse; border-spacing: 0px; background-color: transparent; width: 600px; \" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" > <tr style=\"border-collapse: collapse\"> <td align=\"left\" style=\" margin: 0; padding-left: 10px; padding-right: 10px; padding-top: 15px; padding-bottom: 15px; \" > <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\" border-collapse: collapse; border-spacing: 0px; float: right; \" > <tr style=\"border-collapse: collapse\"> <td align=\"left\" style=\"padding: 0; margin: 0; width: 278px\" ></td> </tr> </table> </td> </tr> </table> </td> </tr> </table> <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\" border-collapse: collapse; border-spacing: 0px; table-layout: fixed !important; width: 100%; background-color: transparent; background-repeat: repeat; background-position: center top; \" > <tr style=\"border-collapse: collapse\"> <td align=\"center\" style=\"padding: 0; margin: 0\"> <table class=\"es-header-body\" style=\" border-collapse: collapse; border-spacing: 0px; background-color: #ffffff; width: 600px; \" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" > <tr style=\"border-collapse: collapse\"> <td style=\" margin: 0; padding-top: 30px; padding-bottom: 30px; padding-left: 40px; padding-right: 40px; background-color: #049474 ; \" align=\"left\" > <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse: collapse; border-spacing: 0px\" > <tr style=\"border-collapse: collapse\"> <td valign=\"top\" align=\"center\" style=\" font-weight: bold; padding: 0; margin: 0; width: 520px; color: #fff; \" > Centro de Desarrollo de Software   </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\" border-collapse: collapse; border-spacing: 0px; table-layout: fixed !important; width: 100%; \" > <tr style=\"border-collapse: collapse\"> <td align=\"center\" style=\"padding: 0; margin: 0\"> <table class=\"es-content-body\" style=\" border-collapse: collapse; border-spacing: 0px; background-color: #049474 ; width: 600px; \" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#049474 \" align=\"center\" > <tr style=\"border-collapse: collapse\"> <td style=\" margin: 0; padding-top: 40px; padding-bottom: 40px; padding-left: 40px; padding-right: 40px; background-image: url(https://images.app.goo.gl/brtjQC1D3Dokogf17); background-repeat: no-repeat; background-position: left top; \" align=\"left\" background=\"\" > <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse: collapse; border-spacing: 0px\" > <tr style=\"border-collapse: collapse\"> <td valign=\"top\" align=\"center\" style=\"padding: 0; margin: 0; width: 520px\" > <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\" border-collapse: collapse; border-spacing: 0px; \" > <tr style=\"border-collapse: collapse\"> <td align=\"center\" style=\" padding: 0; margin: 0; padding-bottom: 10px; padding-top: 40px; \" > <h1 style=\" margin: 0; line-height: 36px; font-family: lato, 'helvetica neue', helvetica, arial, sans-serif; font-size: 30px; font-style: normal; font-weight: bold; color: #ffffff; \" > " + userDTO.getPerson().getName() + " </h1> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\" border-collapse: collapse; border-spacing: 0px; table-layout: fixed !important; width: 100%; \" > <tr style=\"border-collapse: collapse\"> <td align=\"center\" style=\"padding: 0; margin: 0\"> <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\" border-collapse: collapse; border-spacing: 0px; background-color: #ffffff; width: 600px; \" > <tr style=\"border-collapse: collapse\"> <td align=\"left\" style=\" padding: 0; margin: 0; padding-top: 40px; padding-left: 40px; padding-right: 40px; \" > <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse: collapse; border-spacing: 0px\" > <tr style=\"border-collapse: collapse\"> <td valign=\"top\" align=\"center\" style=\"padding: 0; margin: 0; width: 520px\" > <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\" border-collapse: collapse; border-spacing: 0px; \" > <tr style=\"border-collapse: collapse\"> <td class=\"es-m-txt-c\" align=\"left\" style=\" padding: 0; margin: 0; padding-top: 5px; padding-bottom: 15px; \" >  </td> </tr> <tr style=\"border-collapse: collapse\"> <td align=\"left\" style=\" padding: 0; margin: 0; padding-bottom: 10px; \" > <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif; text-align: center; color: #555555;    \">Centro de Desarrollo de Software</h1>\n" +
                " <h2 style=\"font-size:16px;margin:0 0 20px 0; color: #555555; font-family:Arial,sans-serif;\">Código de recuperación de contraseña</h2> <p style=\" margin: 0; -webkit-text-size-adjust: none; -ms-text-size-adjust: none; font-family: helvetica, 'helvetica neue', arial, verdana, sans-serif; line-height: 23px; color: #555555; font-size: 15px; \" > <strong>" + userDTO.getCode()+ "</strong> </p><p>" + userDTO.getUsername() + "</p> </td> </tr> </table> </td> </tr> </table> </td> </tr>\n" +
                "            <td style=\"padding:30px;background:#042b61 ; width:100%;\">\n" +
                "              <table role=\"presentation\" style=\"width:100%; he border-collapse:collapse;border:0;border-spacing:0;font-size:9px;font-family:Arial,sans-serif;\">\n" +
                "                <tr>\n" +
                "                  <td style=\"padding:0;width:50%;\" align=\"center\" height:16px;>\n" +
                "                    <p style=\"margin:0;font-size:14px;line-height:16px;font-family:Arial,sans-serif;color:#ffffff;\">\n" +
                "                     Favor de no responder este correo electrónico, ya que fue envíado por un sistema automátizado. <br/>\n" +
                "                    </p>\n" +
                "                  </td>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr></td> </tr> </table> </td> </tr> </table> </div> </body> </html>";
        return htmlEmail;

    }
    public String code()
    {
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.MONTH, 11);
        c1.set(Calendar.DATE, 05);
        c1.set(Calendar.YEAR, 1996);
        Date dateOne = c1.getTime();
        String code = ""+dateOne.getTime();

        return code;
    }

}
