package al.ib.lawyer.connection;


import al.ib.lawyer.model.SecheduledMeetings;
import al.ib.lawyer.model.aboutus.AboutUsModel;
import al.ib.lawyer.model.addrequest.AddRequestModel;
import al.ib.lawyer.model.allrequests.AllRequestsModel;
import al.ib.lawyer.model.calimmodel.ClaimModel;
import al.ib.lawyer.model.caserequest.CaseRequestModel;
import al.ib.lawyer.model.contactus.ContactUSModel;
import al.ib.lawyer.model.contract.DraftContractModel;
import al.ib.lawyer.model.cscanclemeeting.CanceledMeetingModel;
import al.ib.lawyer.model.customermeeting.CustomerMeetingModel;
import al.ib.lawyer.model.customerregist.CustomerRegisterationModel;
import al.ib.lawyer.model.history.HistoryRequest;
import al.ib.lawyer.model.knetmodel.KnetModel;
import al.ib.lawyer.model.lawyer.LawyerModel;
import al.ib.lawyer.model.lawyermeeting.LawyerMeetingModel;
import al.ib.lawyer.model.lawyerregister.LawyerRegisterModel;
import al.ib.lawyer.model.lawyerrequests.LawyerOpenRequestsModel;
import al.ib.lawyer.model.lawyers.LawyersModel;
import al.ib.lawyer.model.login.LoginModel;
import al.ib.lawyer.model.lookupdata.LookUpDataModel;
import al.ib.lawyer.model.packagedetails.PackageDetailsModel;
import al.ib.lawyer.model.packagemodel.PackageModel;
import al.ib.lawyer.model.privacymodel.PrivacyModel;
import al.ib.lawyer.model.privaterequest.PrivateRequestModel;
import al.ib.lawyer.model.qutation.QutationModel;
import al.ib.lawyer.model.requestDetails.RequestDetailsModel;
import al.ib.lawyer.model.simple.SimpleModelResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;

import retrofit2.http.POST;


public interface ApiInterface {


    @POST("frmLawyerService.aspx/")
    Call<al.ib.lawyer.model.login.LoginModel> login(
            @Header("MethodName") String methodName,
            @Header("x-user") String userName,
            @Header("x-pass") String pass,
            @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<LawyerRegisterModel> registerLawyer(@Header("MethodName") String methodName,
                                             @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<LawyerOpenRequestsModel> lawyerOpenRequests(@Header("MethodName") String methodName /*GetAllOpenRequests*/,
                                                     @Header("x-user") String userName,
                                                     @Header("x-pass") String pass,
                                                     @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<LawyerModel> getLawyerByID(@Header("MethodName") String methodName,
                                    @Header("x-user") String userName,
                                    @Header("x-pass") String pass,
                                    @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<LawyerMeetingModel> getLawyerOpenRequests(@Header("MethodName") String methodName,
                                                   @Header("x-user") String userName,
                                                   @Header("x-pass") String pass,
                                                   @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<al.ib.lawyer.model.meeting.LawyerMeetingModel> getLawyerComingMeeting(@Header("MethodName") String methodName,
                                                                               @Header("x-user") String userName,
                                                                               @Header("x-pass") String pass,
                                                                               @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<SecheduledMeetings> getLawyerScheduledMeeting(@Header("MethodName") String methodName,
                                                       @Header("x-user") String userName,
                                                       @Header("x-pass") String pass,
                                                       @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<LawyerMeetingModel> getLawyerCanceledMeeting(@Header("MethodName") String methodName,
                                                      @Header("x-user") String userName,
                                                      @Header("x-pass") String pass,
                                                      @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<HistoryRequest> getRequestHistoryLawyer(@Header("MethodName") String methodName,
                                                 @Header("x-user") String userName,
                                                 @Header("x-pass") String pass,
                                                 @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<PrivateRequestModel> getPrivateRequestsLawyer(@Header("MethodName") String methodName,
                                                       @Header("x-user") String userName,
                                                       @Header("x-pass") String pass,
                                                       @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<AllRequestsModel> getAllOpenRequests(@Header("MethodName") String methodName,
                                              @Header("x-user") String userName,
                                              @Header("x-pass") String pass);

    @POST("frmLawyerService.aspx/")
    Call<ContactUSModel> sendContactUs(@Header("MethodName") String methodName,
                                       @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<PrivacyModel> getPrivacy(@Header("MethodName") String methodName);

    @POST("frmLawyerService.aspx/")
    Call<AboutUsModel> getAboutUs(@Header("MethodName") String methodName);

    @POST("frmLawyerService.aspx/")
    Call<CustomerRegisterationModel> customerRegistration(@Header("MethodName") String methodName,
                                                          @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<CustomerMeetingModel> getCustomerMeetings(@Header("MethodName") String methodName,
                                                   @Header("x-user") String userName,
                                                   @Header("x-pass") String pass,
                                                   @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<CanceledMeetingModel> getCustomerCanceledMeetings(@Header("MethodName") String methodName,
                                                           @Header("x-user") String userName,
                                                           @Header("x-pass") String pass,
                                                           @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<LawyersModel> getLawyers(@Header("MethodName") String methodName,
                                  @Header("x-user") String userName,
                                  @Header("x-pass") String pass,
                                  @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<CaseRequestModel> addCaseRequest(@Header("MethodName") String methodName,
                                          @Header("x-user") String userName,
                                          @Header("x-pass") String pass,
                                          @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<ClaimModel> sendClaimAndComplain(@Header("MethodName") String methodName,
                                          @Header("x-user") String userName,
                                          @Header("x-pass") String pass,
                                          @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<LookUpDataModel> getLookUpData(@Header("MethodName") String methodName,
                                        @Header("x-user") String userName,
                                        @Header("x-pass") String pass);


    @POST("frmLawyerService.aspx/")
    Call<LookUpDataModel> changePassword(@Header("MethodName") String methodName,
                                         @Header("x-user") String userName,
                                         @Header("x-pass") String pass,
                                         @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<RequestDetailsModel> getRequestDetails(@Header("MethodName") String methodName,
                                                @Header("x-user") String userName,
                                                @Header("x-pass") String pass,
                                                @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<SimpleModelResponse> addAttachment(@Header("MethodName") String methodName,
                                            @Header("x-user") String userName,
                                            @Header("x-pass") String pass,
                                            @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<DraftContractModel> draftContract(@Header("MethodName") String methodName,
                                           @Header("x-user") String userName,
                                           @Header("x-pass") String pass,
                                           @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<SimpleModelResponse> updateCaseRequest(@Header("MethodName") String methodName,
                                                @Header("x-user") String userName,
                                                @Header("x-pass") String pass,
                                                @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<SimpleModelResponse> updateCustomerProfile(@Header("MethodName") String methodName,
                                                    @Header("x-user") String userName,
                                                    @Header("x-pass") String pass,
                                                    @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<SimpleModelResponse> updateLawyerProfile(@Header("MethodName") String methodName,
                                                  @Header("x-user") String userName,
                                                  @Header("x-pass") String pass,
                                                  @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<SimpleModelResponse> addNewMeeting(@Header("MethodName") String methodName,
                                            @Header("x-user") String userName,
                                            @Header("x-pass") String pass,
                                            @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<SimpleModelResponse> rescheduleMeet(@Header("MethodName") String methodName,
                                             @Header("x-user") String userName,
                                             @Header("x-pass") String pass,
                                             @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<SimpleModelResponse> canceleMeet(@Header("MethodName") String methodName,
                                          @Header("x-user") String userName,
                                          @Header("x-pass") String pass,
                                          @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<QutationModel> addQutation(@Header("MethodName") String methodName,
                                    @Header("x-user") String userName,
                                    @Header("x-pass") String pass,
                                    @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<SimpleModelResponse> forgetEmail(@Header("MethodName") String methodName,
                                          @Body RequestBody body);


    @POST("frmLawyerService.aspx/")
    Call<PackageModel> getAllPackageModel(@Header("MethodName") String methodName,
                                          @Header("x-user") String userName,
                                          @Header("x-pass") String pass);

    @POST("frmLawyerService.aspx/")
    Call<PackageDetailsModel> getLawyerPackage(@Header("MethodName") String methodName,
                                               @Header("x-user") String userName,
                                               @Header("x-pass") String pass,
                                               @Body RequestBody body);

    @POST("frmLawyerService.aspx/")
    Call<KnetModel> directToKnetUrl(@Header("MethodName") String methodName,
                                    @Header("x-user") String userName,
                                    @Header("x-pass") String pass,
                                    @Body RequestBody body);
}
