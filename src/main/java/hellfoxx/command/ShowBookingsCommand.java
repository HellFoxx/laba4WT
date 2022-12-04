package hellfoxx.command;

import hellfoxx.constant.PageConstant;
import hellfoxx.constant.RequestConstant;
import hellfoxx.entity.Booking;
import hellfoxx.entity.User;
import hellfoxx.exception.ServiceException;
import hellfoxx.message.MessageHandler;
import hellfoxx.service.CommonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowBookingsCommand implements Command {

    private static Logger log = LogManager.getLogger("show bookings");

    private CommonService commonService;

    public ShowBookingsCommand(CommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        User user = (User) requestContent.getSessionAttribute(RequestConstant.USER);
        Map<String, Object> requestAttributes = new HashMap<>();
        try {
            List<Booking> bookingList = commonService.findBookings(user.getLogin());
            if (bookingList.isEmpty()) {
                requestAttributes.put(RequestConstant.ERROR_FINDING_BOOKINGS, MessageHandler.getMessage(
                        "message.no_bookings", (String) requestContent.getSessionAttribute(RequestConstant.LOCALE)));
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, PageConstant.USER_MAIN_PAGE,
                        requestAttributes);
            } else {
                requestAttributes.put(RequestConstant.BOOKINGS, bookingList);
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, PageConstant.BOOKINGS_PAGE,
                        requestAttributes);
            }
            return commandResult;
        } catch (ServiceException e) {
            log.error("Error in receiving bookings");
            return new DefaultCommand().execute(requestContent);
        }
    }
}
