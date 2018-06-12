package io.github.alizarion.common.api;

import com.itesoft.common.utils.security.delegated.APIKeyProvider;
import com.itesoft.common.utils.security.delegated.ApiKeyManagerProvider;
import com.itesoft.common.utils.security.model.ClientApi;

import javax.inject.Named;
import java.security.Principal;
import java.util.List;

@APIKeyProvider
@Named
public class MyApiKeyManagerProvider extends ApiKeyManagerProvider {
    @Override
    public ClientApi update(Principal principal, ClientApi clientApi, String s) throws Exception {
        return null;
    }

    @Override
    public ClientApi create(Principal principal, ClientApi clientApi) throws Exception {
        return null;
    }

    @Override
    public ClientApi getById(Principal principal, String s) throws Exception {
        return null;
    }

    @Override
    public List<ClientApi> getAll(Principal principal, Integer integer, Integer integer1) throws Exception {
        return null;
    }

    @Override
    public Boolean drop(Principal principal, String s) throws Exception {
        return null;
    }
}
