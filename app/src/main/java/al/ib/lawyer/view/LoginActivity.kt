package al.ib.lawyer.view

import al.ib.lawyer.MainActivity
import al.ib.lawyer.R
import al.ib.lawyer.app.UserManager
import al.ib.lawyer.app.UserManager.LAWYER
import al.ib.lawyer.app.UserManager.USER
import al.ib.lawyer.connection.ApiClient
import al.ib.lawyer.connection.ApiInterface
import al.ib.lawyer.connection.RetrofitCallBack
import al.ib.lawyer.model.User
import al.ib.lawyer.model.lawyer.LawyerModel
import al.ib.lawyer.model.login.LoginModel
import al.ib.lawyer.model.simple.SimpleModelResponse
import al.ib.lawyer.view.register.CustomerRegistration
import al.ib.lawyer.view.register.LawyerRegistration
import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

    }

    private val progressBar: ProgressBar?
        get() {
            val progress = findViewById<ProgressBar>(R.id.progress)
            return progress
        }

    private val scrollView: ScrollView?
        get() {
            val view = findViewById<ScrollView>(R.id.wholeView)
            return view
        }

    private fun initView() {
        var userName = findViewById<EditText>(R.id.loginUserName)
        val password = findViewById<EditText>(R.id.loginPassword)
        val forgetPass = findViewById<TextView>(R.id.forgetPass)
        val login = findViewById<TextView>(R.id.login)
        val remember = findViewById<CheckBox>(R.id.rememberBox)
        val registerNow = findViewById<TextView>(R.id.registerNow)
        val progress = progressBar
        val view = scrollView


        val dialog = initDialog()

        var haveToSaveLogin = false

        val lawyerIdCall = RetrofitCallBack(this, object : Callback<LawyerModel> {
            override fun onResponse(call: Call<LawyerModel>, response: Response<LawyerModel>) {

                if (response.isSuccessful && response.body()?.result?.result?.equals("OK")!!) {
                    val userManager = UserManager(this@LoginActivity)
                    userManager.completeUser(User(
                            response.body()!!.result.lawyer.get(0).description,
                            response.body()!!.result.lawyer.get(0).descriptionAR,
                            response.body()!!.result.lawyer.get(0).majorcaseDescription,
                            response.body()!!.result.lawyer.get(0).majorcaseDescriptionAr,
                            response.body()!!.result.lawyer.get(0).memberNo,
                            response.body()!!.result.lawyer.get(0).consultationFee,
                            response.body()!!.result.lawyer.get(0).officeTimingEn,
                            response.body()!!.result.lawyer.get(0).officeTimingAr,
                            response.body()!!.result.lawyer.get(0).officePhone1,
                            response.body()!!.result.lawyer.get(0).officePhone2,
                            response.body()!!.result.lawyer.get(0).officeAddressAr,
                            response.body()!!.result.lawyer.get(0).officeAddressEn,
                            response.body()!!.result.lawyer.get(0).pictureURL
                    ))
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()

                }
            }

            override fun onFailure(call: Call<LawyerModel>, t: Throwable) {

            }
        }, null, null)

        val call = RetrofitCallBack(this, object : Callback<LoginModel> {
            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {

                Log.d("LoginActivity", response.isSuccessful.toString() + ", " + response.body()?.result?.result)
                if (response.isSuccessful && response.body()?.result?.result?.equals("OK")!!) {


                    if (remember.isChecked) {
                        haveToSaveLogin = true
                    }

                    val jsonObject = JSONObject()
                    jsonObject.put("LawyerID", response.body()!!.result.customer.get(0).lawyerID)
                    Log.d(TAG, jsonObject.toString())
                    val type = MediaType.parse("application/raw")
                    val body = RequestBody.create(type, jsonObject.toString())


                    val userManager = UserManager(applicationContext)
                    userManager.createUser(User(response.body()!!.result.customer.get(0).id,
                            response.body()!!.result.customer.get(0).customerId,
                            response.body()!!.result.customer.get(0).password,
                            response.body()!!.result.customer.get(0).ksalt,
                            response.body()!!.result.customer.get(0).email,
                            response.body()!!.result.customer.get(0).lawyerID,
                            response.body()!!.result.customer.get(0).fullName,
                            response.body()!!.result.customer.get(0).fullNameAr,
                            response.body()!!.result.customer.get(0).fullNameEn,
                            response.body()!!.result.customer.get(0).phone,
                            response.body()!!.result.customer.get(0).gender,
                            response.body()!!.result.customer.get(0).pictureId,
                            response.body()!!.result.customer.get(0).createdOnUtc,
                            response.body()!!.result.customer.get(0).lastLoginDateUtc,
                            response.body()!!.result.customer.get(0).lastActivityDateUtc,
                            response.body()!!.result.customer.get(0).isIsLawyer,
                            response.body()!!.result.customer.get(0).isActive,
                            response.body()!!.result.customer.get(0).isDeleted), haveToSaveLogin)

                    if (response.body()!!.result.customer.get(0).isIsLawyer)
                        userManager.userType = UserManager.LAWYER
                    else userManager.userType = UserManager.USER

                    if (response.body()!!.result.customer.get(0).isIsLawyer)
                        ApiClient.getClient().create(ApiInterface::class.java).getLawyerByID("GetLawyerByID",
                                response.body()!!.result.customer.get(0).email,
                                response.body()!!.result.customer.get(0).password, body).enqueue(RetrofitCallBack(
                                this@LoginActivity, lawyerIdCall, progress, view))
                    else {

                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    }

                } else Toast.makeText(applicationContext, response.body()!!.result.details, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<LoginModel>, t: Throwable) {

            }
        }, null, null)

        registerNow.setOnClickListener { dialog.show() }
        login.setOnClickListener {
            // startActivity(Intent(this, MainActivity::class.java))
            val jsonObject = JSONObject()
            jsonObject.put("UserName", userName.text.toString())
            jsonObject.put("Password", password.text.toString())
            Log.d(TAG, jsonObject.toString())
            val type = MediaType.parse("application/raw")
            val body = RequestBody.create(type, jsonObject.toString())
            ApiClient.getClient().create(ApiInterface::class.java).login("Login",
                    userName.text.toString(), password.text.toString(), body)
                    .enqueue(RetrofitCallBack(this, call, progress, view))
        }

        forgetPass.setOnClickListener {
            initForgetPassDialog().show()
        }

    }

    private fun initDialog(): Dialog {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_register_type)

        val customerType = dialog.findViewById<TextView>(R.id.registerCustomer);
        val lawyerType = dialog.findViewById<TextView>(R.id.registerLawyer);


        val userManager = UserManager(this)
        customerType.setOnClickListener {
            dialog.dismiss()
            userManager.userType = USER
            startActivity(Intent(this, CustomerRegistration::class.java))
        }
        lawyerType.setOnClickListener {
            dialog.dismiss()
            userManager.userType = LAWYER
            startActivity(Intent(this, LawyerRegistration::class.java))
        }
        return dialog
    }

    private fun initForgetPassDialog(): Dialog {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.reset_pass_dialog)

        val email = dialog.findViewById<TextView>(R.id.email);
        val send = dialog.findViewById<TextView>(R.id.send);

        val call = RetrofitCallBack(this, object : Callback<SimpleModelResponse> {
            override fun onResponse(call: Call<SimpleModelResponse>, response: Response<SimpleModelResponse>) {

                Toast.makeText(this@LoginActivity, response.body()!!.result.details, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<SimpleModelResponse>, t: Throwable) {

            }
        }, null, null)

        send.setOnClickListener {
            val jsonObject = JSONObject()
            jsonObject.put("Email", email.text.toString())
            Log.d(TAG, jsonObject.toString())
            val type = MediaType.parse("application/raw")
            val body = RequestBody.create(type, jsonObject.toString())
            ApiClient.getClient().create(ApiInterface::class.java).forgetEmail("ForgetMyPassword", body)
                    .enqueue(RetrofitCallBack(this, call, progressBar, scrollView))
        }


        return dialog
    }

}
