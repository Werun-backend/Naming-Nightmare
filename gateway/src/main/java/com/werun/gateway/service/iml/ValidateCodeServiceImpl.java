package com.werun.gateway.service.iml;

import com.google.code.kaptcha.Producer;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.werun.common.core.constant.CacheConstants;
import com.werun.common.core.constant.Constants;
import com.werun.common.core.exception.CaptchaException;
import com.werun.common.core.request.Result;
import com.werun.common.core.utils.StringUtils;
import com.werun.common.core.utils.UUID;
import com.werun.gateway.config.properties.CaptchaProperties;
import com.werun.gateway.domain.Captcha;
import com.werun.gateway.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    @Autowired
    private CaptchaProperties captchaProperties;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Result<Captcha> createCaptcha() throws IOException, CaptchaException {
        boolean captchaEnabled = captchaProperties.getEnabled();

        if (!captchaEnabled)
        {
            return Result.ok(new Captcha(captchaEnabled,null,null));
        }
        String uuid = UUID.randomUUID().toString(true);
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        String captchaType = captchaProperties.getType();
        // 生成验证码
        if ("math".equals(captchaType))
        {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if ("char".equals(captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        if (code != null) {
            stringRedisTemplate.opsForValue().set(verifyKey,code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        }
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            if (image != null) {
                ImageIO.write(image, "jpg", os);
            }
        }
        catch (IOException e)
        {
            return Result.fail(e.getMessage());
        }
        Captcha captcha = new Captcha(true, uuid, Base64.encode(os.toByteArray()));
        return Result.ok(captcha);
    }

    @Override
    public void checkCaptcha(String code, String uuid) throws CaptchaException {
        if(StringUtils.isEmpty(code)){
            throw new CaptchaException("验证码不能为空");
        }
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = stringRedisTemplate.opsForValue().get(verifyKey);
        if(captcha==null){
            throw new CaptchaException("验证码已失效");
        }
        stringRedisTemplate.delete(verifyKey);
        if(!code.equalsIgnoreCase(captcha)){
            throw new CaptchaException(code);
        }
    }
}
