package tv.fengmang.ccpush.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tv.fengmang.ccpush.Response;
import tv.fengmang.ccpush.bean.OnlineUser;
import tv.fengmang.ccpush.cc.CcMessageHelper;
import tv.fengmang.ccpush.cc.ChannelContainer;
import tv.fengmang.ccpush.cc.NettyChannel;
import tv.fengmang.ccpush.proto.CcMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ccpush")
public class PushController {

    @GetMapping("get_online_user_list")
    public Response onlineList(@RequestParam int pageIndex, @RequestParam int pageSize) {
        Map<String, NettyChannel> map = ChannelContainer.getInstance().getAll();
        if (map.isEmpty()) {
            return Response.success();
        } else {
            List<OnlineUser> userList = new ArrayList<>();
            for (Map.Entry<String, NettyChannel> entry : map.entrySet()) {
                OnlineUser onlineUser = new OnlineUser();
                onlineUser.setIp(entry.getValue().getChannel().remoteAddress().toString());
                onlineUser.setUserId(entry.getValue().getUserId());
                onlineUser.setLoginTime(0);
                userList.add(onlineUser);
            }
            Response<List<OnlineUser>> response = Response.success();
            response.setData(userList);
            return response;
        }
    }

    @GetMapping("send")
    public Response sendMsg(@RequestParam String fromId, @RequestParam String content) {
        NettyChannel nettyChannel = ChannelContainer.getInstance().getActiveChannelByUserId(fromId);
        if (nettyChannel != null) {
            CcMessage.Msg pushMsg = CcMessageHelper.createNormalMsg(fromId, content);
            nettyChannel.getChannel().writeAndFlush(pushMsg);
        } else {
            return Response.failed();
        }
        return Response.success();
    }

    @GetMapping(path = "dev")
    public Response testGetStr() {
        return Response.success();
    }
}
