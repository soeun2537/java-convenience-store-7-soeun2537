package store.dto.request.input;

import store.util.CommonParser;
import store.util.CommonValidator;

public class MembershipApplicationStatusRequest {

    private final boolean membershipApplicationStatus;

    private MembershipApplicationStatusRequest(boolean membershipApplicationStatus) {
        this.membershipApplicationStatus = membershipApplicationStatus;
    }

    public static MembershipApplicationStatusRequest from(String input) {
        CommonValidator.validateNotNull(input);
        CommonValidator.validateYesOrNo(input);
        boolean status = CommonParser.parseBoolean(input);

        return new MembershipApplicationStatusRequest(status);
    }

    public boolean isMembershipApplicationStatus() {
        return membershipApplicationStatus;
    }
}
