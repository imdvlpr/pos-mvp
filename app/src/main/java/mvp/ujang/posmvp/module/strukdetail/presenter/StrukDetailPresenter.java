package mvp.ujang.posmvp.module.strukdetail.presenter;

import android.content.Context;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.struk.StrukContract;
import mvp.ujang.posmvp.module.struk.model.Struk;
import mvp.ujang.posmvp.module.strukdetail.StrukDetailContract;
import mvp.ujang.posmvp.module.strukdetail.model.StrukDetail;
import mvp.ujang.posmvp.usecase.struk.StrukUsecase;
import mvp.ujang.posmvp.usecase.strukdetail.StrukDetailUsecase;

public class StrukDetailPresenter implements StrukDetailContract.Presenter {

    private StrukDetailContract.StrukView view;
    private StrukDetailUsecase strukUsecase;
    private Context context;

    public StrukDetailPresenter(StrukDetailUsecase strukUsecase,
                                StrukDetailContract.StrukView view,
                                Context context) {
        this.strukUsecase = strukUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadStruk(null);
    }



    @Override
    public void loadStruk(StrukDetail strukDetail) {
        strukUsecase.loadStruk(strukDetail,new Callback.LoadCallback<StrukDetail>() {
            @Override
            public void onLoadSuccess(List<StrukDetail> response) {
                view.listStruk(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }
}
