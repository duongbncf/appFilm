package com.example.appfilm_2.frament;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appfilm_2.R;
import com.example.appfilm_2.ui.SignInAcitivity;
import com.example.appfilm_2.ui.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuFragment extends Fragment {
    ImageView vAvatar;
    TextView tvName;
    TextView tvEmail;
    ImageView vSignOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu,container,false);
    vAvatar = view.findViewById(R.id.vAvatar);
    tvName = view.findViewById(R.id.tvName);
    tvEmail = view.findViewById(R.id.tvEmail);
    vSignOut = view.findViewById(R.id.vSignOut);
    showUser();
        return view;

    }

    private void showUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
                return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        if(name == null){
            tvName.setVisibility(getView().GONE);
        }else{
            tvName.setVisibility(getView().VISIBLE);
            tvName.setText(name);
        }
        tvEmail.setText(email);
        vSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent  = new Intent(requireContext(), SignInAcitivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}
