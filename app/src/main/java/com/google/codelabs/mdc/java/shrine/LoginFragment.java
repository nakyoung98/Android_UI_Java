package com.google.codelabs.mdc.java.shrine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.codelabs.mdc.java.shrine.databinding.ShrLoginFragmentBinding;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {

    private ShrLoginFragmentBinding shrLoginFragmentBinding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        shrLoginFragmentBinding = ShrLoginFragmentBinding.inflate(inflater,container,false);
        View view = shrLoginFragmentBinding.getRoot();
        // Snippet from "Navigate to the next Fragment" section goes here.


        //버튼 클릭 리스너 구현
        shrLoginFragmentBinding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPasswordValid(shrLoginFragmentBinding.passwordEditText.getText())){
                    //오류 발샹시 오류 문구 구현
                    shrLoginFragmentBinding.passwordTextInput.setError(getString(R.string.shr_error_password));
                    Log.i("Password","Error");
                }else{
                    shrLoginFragmentBinding.passwordTextInput.setError(null); //오류 없으면 오류 문구 삭제
                    Log.i("getNowActivity", getActivity().toString());
                    ((NavigationHost)getActivity()).navigateTo(new ProductGridFragment(),false);
                }
            }
        });

        //password칸에서 Error 체크
        shrLoginFragmentBinding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isPasswordValid(editable)){
                    shrLoginFragmentBinding.passwordTextInput.setError(null); //오류 없으면 오류 문구 삭제
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding 후 삭제시에는 무조건 null. 메모리 누수 방지를 위함
        shrLoginFragmentBinding = null;
    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    private boolean isPasswordValid(@Nullable Editable text){
        return text != null && text.length() >= 8;
    }
}
