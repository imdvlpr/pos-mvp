package mvp.ujang.posmvp.module.struk.presenter;

import android.content.Context;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.refund.view.RefundDetailActivity;
import mvp.ujang.posmvp.module.struk.StrukContract;
import mvp.ujang.posmvp.module.struk.model.Struk;
import mvp.ujang.posmvp.module.struk.view.StrukFragment;
import mvp.ujang.posmvp.usecase.struk.StrukUsecase;
import mvp.ujang.posmvp.utils.Common;

public class StrukPresenter implements StrukContract.Presenter {

    private StrukContract.StrukView view;
    private StrukUsecase strukUsecase;
    private Context context;
    private String TAG = StrukFragment.class.getSimpleName();

    public StrukPresenter(StrukUsecase strukUsecase,
                          StrukContract.StrukView view,
                          Context context) {
        this.strukUsecase = strukUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadStruk();
    }


    @Override
    public void loadStruk() {
        final long startTime = System.currentTimeMillis();
        strukUsecase.loadStruk(new Callback.LoadCallback<Struk>() {
            @Override
            public void onLoadSuccess(List<Struk> response) {
                view.listStruk(response);
                Common.printTimeMillis(TAG+" Load Data Struk",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Struk",startTime,System.currentTimeMillis());
            }
        });
    }
}
