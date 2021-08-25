package hello_world.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import hello_world.constants.Hello_worldPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate = true,
        properties = {
                "javax.portlet.name=" + Hello_worldPortletKeys.HELLO_WORLD,
                "mvc.command.name=/create/edit"
        }
)
public class CreateEditRenderMvcCommand implements MVCRenderCommand {
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        return "/edit.jsp";
    }
}
