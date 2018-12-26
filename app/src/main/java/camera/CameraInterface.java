package camera;

import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import util.CamParaUtil;

public class CameraInterface {
    private static final String TAG="yanzi";
    private Camera mCamera;
    private Camera.Parameters mParams;
    private boolean isPreviewing=false;
    private float mPreviewRate=-1f;
    private  static CameraInterface mCameraInterface;

    public  interface CamOpenOverCallBack{
        public void cameraHasOpend();
    }

    private CameraInterface(){
    }

    public static synchronized CameraInterface getInstance(){
        if(mCameraInterface==null){
            mCameraInterface=new CameraInterface();
        }
        return mCameraInterface;
    }
//打开camera
    public void doOpenCamera(CamOpenOverCallBack callback){
        Log.i(TAG, "Camera open…… ");
        mCamera=Camera.open();
        Log.i(TAG, "Camera open over…… ");
        callback.cameraHasOpend();
    }
    //开启预览
    public void doStartPreview(SurfaceHolder holder,float previewRate){
        Log.i(TAG, "doStartPreview:……");
        if (isPreviewing){
            mCamera.stopPreview();
            return;
        }
        if (mCamera!=null){
            mParams=mCamera.getParameters();
            mParams.setPictureFormat(PixelFormat.JPEG);
            CamParaUtil.getInstace().printSupportPictureSize(mParams);
            CamParaUtil.getInstace().printSupportPreviewSize(mParams);
            //设置Previewsize和picturesize
        }
    }

}
