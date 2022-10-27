package com.example.truongvivuon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsernamePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsernamePasswordFragment extends Fragment {

    TextInputEditText tvLastName, tvFirstName, tvEmail, tvPassword, tvConfirmPassword;
    Button btnRegister;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    String userID;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsernamePasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsernamePasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsernamePasswordFragment newInstance(String param1, String param2) {
        UsernamePasswordFragment fragment = new UsernamePasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_username_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();

        tvFirstName =view.findViewById(R.id.tvFirstName);
        tvLastName = view.findViewById(R.id.tvLastName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPassword = view.findViewById(R.id.tvPassword);
        tvConfirmPassword = view.findViewById(R.id.tvConfirmPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
        boolean test = true;
        if (tvEmail.getText().toString().isEmpty()) {
            tvEmail.setError("Vui lòng nhập dữ liệu!!!");
            test = false;
        }
        if (tvEmail.getText().toString().isEmpty()) {
            tvEmail.setError("Vui lòng nhập dữ liệu!!!");
            test = false;
        }
        if (tvPassword.getText().toString().isEmpty()) {
            tvPassword.setError("Vui lòng nhập dữ liệu!!!");
            test = false;
        }
        if (tvConfirmPassword.getText().toString().isEmpty()) {
            tvConfirmPassword.setError("Vui lòng nhập dữ liệu!!!");
            test = false;
        }
        if (!(tvPassword.getText().toString().equals(tvConfirmPassword.getText().toString()))) {
            tvConfirmPassword.setError("Mật khẩu không trùng khớp!!!");
            test = false;
            return;
        }
        if (test == true) {
            String address = getArguments().getString("address");
            String firstname = getArguments().getString("firstname");
            String lastname = getArguments().getString("lastname");
            double latitude = getArguments().getDouble("latitude");
            double longitude = getArguments().getDouble("longitude");
            String mobile = getArguments().getString("mobile");
            String email = tvEmail.getText().toString();
            String password = tvPassword.getText().toString();


            fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isComplete()) {
                                userID = fAuth.getCurrentUser().getUid();
                                DatabaseReference databaseReference = fDatabase.getReference();

                                Map<String, Object> user = new HashMap<>();
                                user.put("firstname", firstname);
                                user.put("lastname", lastname);
                                user.put("address", address);
                                user.put("email", email);
                                user.put("mobile", mobile);
                                user.put("latitude", latitude);
                                user.put("longitude", longitude);
                                databaseReference.child("users").child(userID).setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getContext(), "OKE", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                                intent.putExtra("email", email);
                                                getActivity().setResult(Activity.RESULT_OK, intent);
                                                getActivity().finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }


//            String address = getArguments().getString("address");
//            String firstname = getArguments().getString("firstname");
//            String lastname = getArguments().getString("lastname");
//            double latitude = getArguments().getDouble("latitude");
//            double longitude = getArguments().getDouble("longitude");
//            String mobile = getArguments().getString("mobile");
//            String email = tvEmail.getText().toString();
//            String password = tvPassword.getText().toString();
//            Toast.makeText(getContext(), email+password, Toast.LENGTH_SHORT).show();
//
//            fAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Toast.makeText(getContext() , "OKKKKK", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            fAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isComplete()) {
//                                userID = fAuth.getCurrentUser().getUid();
//                                DatabaseReference databaseReference = fDatabase.getReference();
//
//                                Map<String, Object> user = new HashMap<>();
//                                user.put("firstname", firstname);
//                                user.put("lastname", lastname);
//                                user.put("address", address);
//                                user.put("email", email);
//                                user.put("mobile", mobile);
//                                user.put("latitude", latitude);
//                                user.put("longitude", longitude);
//                                databaseReference.child("users").child(userID).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Toast.makeText(getContext(), "OKE", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(getContext(), LoginActivity.class);
//                                        intent.putExtra("email", email);
//                                        getActivity().setResult(Activity.RESULT_OK, intent);
//                                        getActivity().finish();
//                                    }
//                                })
//                                        .addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                                            }
//                                        });

//                                Toast.makeText(getContext(), "OOOOO", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
        });
    }
}

