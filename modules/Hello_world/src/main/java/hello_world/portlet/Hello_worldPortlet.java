package hello_world.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import hello_world.constants.Hello_worldPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.liferay.user.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dima
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Hello_world",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + Hello_worldPortletKeys.HELLO_WORLD,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class Hello_worldPortlet extends MVCPortlet {

    @Reference
    private UserService servicePortlet;

    @Reference
    private AssetVocabularyLocalService assetVocabularyLocalService;

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

        renderRequest.setAttribute("usersmock", servicePortlet.initUsers());


        String languageId = LanguageUtil.getLanguageId(renderRequest);
        List<String> journalArticleList = new ArrayList<String>();
        AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
        AssetVocabulary assetVocabulary = null;
        try {
            assetVocabulary = assetVocabularyLocalService.getAssetVocabulary(38309);
            List<AssetCategory> assetCategoryList = assetVocabulary.getCategories();

            assetEntryQuery.setAnyCategoryIds(assetCategoryList.stream().mapToLong(AssetCategoryModel::getCategoryId).toArray());

            List<AssetEntry> assetEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
            JournalArticleResource journalArticleResource = null;

            for (AssetEntry ae : assetEntryList) {
                journalArticleResource = JournalArticleResourceLocalServiceUtil.getJournalArticleResource(ae.getClassPK());
                JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(journalArticleResource.getResourcePrimKey());
                journalArticleList.add(journalArticle.getTitle());
            }
        } catch (PortalException e) {
            e.printStackTrace();
        }

        if (renderRequest.isUserInRole(RoleConstants.ADMINISTRATOR))
            renderRequest.setAttribute("article", journalArticleList);

//		Role role = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ADMINISTRATOR);
//
//		User admin = UserLocalServiceUtil.getRoleUsers(role.getRoleId()).get(0);
//		final ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
//		final User user = themeDisplay.getUser();


        super.render(renderRequest, renderResponse);
    }


    @Activate
    public void asdasd() {
        System.out.println("asdasd");
    }
}