package com.ecloud.androidcodeframework.codeframework.encryption;

import android.util.Log;

import org.apache.http.util.EncodingUtils;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午4:40
 * Description: DES加密类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DesEncryption {
    private final static String TAG = "DesEncryption";
    private final static String strDefaultKey = "lefalyHH";
    // 28
    static final int pc_1_cp[] = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42,
            34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36 };
    // 28
    static final int pc_1_dp[] = { 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46,
            38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
    // 48
    static final int pc_2p[] = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29,
            32 };
    // 16
    static final int ls_countp[] = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2,
            2, 1 };
    // 64
    static final int iip_tab_p[] = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44,
            36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40,
            32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27,
            19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23,
            15, 7 };
    // 64
    static final int _iip_tab_p[] = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47,
            15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13,
            53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51,
            19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17,
            57, 25 };
    // 48
    static final int e_r_p[] = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10,
            11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21,
            22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
    // 32
    static final int local_PP[] = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23,
            26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22,
            11, 4, 25 };
    // [8][4][16]
    static final int ccom_SSS_p[][][] = {
            { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                    { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                    { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                    { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },
            { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                    { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                    { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                    { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },
            { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                    { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                    { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                    { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },
            { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                    { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                    { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                    { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } }, /*
																				 * err
																				 * on
																				 */
            { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                    { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, /*
																			 * err
																			 * on
																			 */
                    { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                    { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },
            { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                    { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                    { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                    { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },
            { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                    { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                    { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                    { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },
            { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                    { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                    { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                    { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };
    byte[][] C = new byte[17][28];
    byte[][] D = new byte[17][28];
    byte[][] K = new byte[17][48];

    public DesEncryption() {

    }

    /*
     * iu2b把int转换成byte
     */
    private static byte iu2b(int input) {
        byte output1;
        output1 = (byte) (input & 0xff);
        return output1;
    }

    /*
     * b2iu把byte按照不考虑正负号的原则的＂升位＂成int程序， 因为java没有unsigned运算
     */
    private static int b2iu(byte b) {
        return b < 0 ? b & 0x7F + 128 : b;
    }

    /*
     * byteHEX()，用来把一个byte类型的数转换成十六进制的ASCII表示，
     * 因为java中的byte的toString无法实现这一点，我们又没有C语言中的 sprintf(outbuf,"%02X",ib)
     */
    public static String byteHEX(byte ib) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        return new String(ob);
    }

    /*
     * desMemcopy是一个内部使用的byte数组的块拷贝函数， 从input的inpos开始把len长度的
     * 字节拷贝到output的outpos位置开始
     */
    private void desMemcpy(byte[] output, byte[] input, int outpos, int inpos,
                           int len) {
        int i;
        for (i = 0; i < len; i++)
            output[outpos + i] = input[inpos + i];
    }

    private void Fexpand0(byte[] in, byte[] out) {
        int divide;
        int i, j;
        byte temp1;
        for (i = 0; i < 8; i++) {
            divide = 7;
            for (j = 0; j < 8; j++) {
                temp1 = in[i];
                out[8 * i + j] = iu2b((b2iu(temp1) >>> divide) & 1);
                divide--;
            }
        }
    }

    private void FLS(byte[] bits, byte[] buffer, int count) {
        int i;
        for (i = 0; i < 28; i++) {
            buffer[i] = bits[(i + count) % 28];
        }
    }

    private void Fson(byte[] cc, byte[] dd, byte[] kk) {
        int i;
        byte[] buffer = new byte[56];
        for (i = 0; i < 28; i++)
            buffer[i] = cc[i];
        for (i = 28; i < 56; i++)
            buffer[i] = dd[i - 28];
        for (i = 0; i < 48; i++)
            kk[i] = buffer[pc_2p[i] - 1];
    }

    private void Fsetkeystar(byte[] bits) {
        int i, j;
        for (i = 0; i < 28; i++)
            C[0][i] = bits[pc_1_cp[i] - 1];
        for (i = 0; i < 28; i++)
            D[0][i] = bits[pc_1_dp[i] - 1];
        for (j = 0; j < 16; j++) {
            FLS(C[j], C[j + 1], ls_countp[j]);
            FLS(D[j], D[j + 1], ls_countp[j]);
            Fson(C[j + 1], D[j + 1], K[j + 1]);
        }
    }

    private void Fiip(byte[] text, byte[] ll, byte[] rr) {
        int i;
        byte[] buffer = new byte[64];
        Fexpand0(text, buffer);
        for (i = 0; i < 32; i++)
            ll[i] = buffer[iip_tab_p[i] - 1];
        for (i = 0; i < 32; i++)
            rr[i] = buffer[iip_tab_p[i + 32] - 1];
    }

    private void Fs_box(byte[] aa, byte[] bb) {
        int i, j, k, m;
        int y, z;
        byte[] ss = new byte[8];
        m = 0;
        for (i = 0; i < 8; i++) {
            j = 6 * i;
            y = b2iu(aa[j]) * 2 + b2iu(aa[j + 5]);
            z = b2iu(aa[j + 1]) * 8 + b2iu(aa[j + 2]) * 4 + b2iu(aa[j + 3]) * 2
                    + b2iu(aa[j + 4]);
            ss[i] = iu2b(ccom_SSS_p[i][y][z]);
            y = 3;
            for (k = 0; k < 4; k++) {
                bb[m++] = iu2b((b2iu(ss[i]) >>> y) & 1);
                y--;
            }
        }
    }

    private void FF(int n, byte[] ll, byte[] rr, byte[] LL, byte[] RR) {
        int i;
        byte[] buffer = new byte[64], tmp = new byte[64];
        for (i = 0; i < 48; i++)
            buffer[i] = rr[e_r_p[i] - 1];
        for (i = 0; i < 48; i++)
            buffer[i] = iu2b((b2iu(buffer[i]) + b2iu(K[n][i])) & 1);
        Fs_box(buffer, tmp);
        for (i = 0; i < 32; i++)
            buffer[i] = tmp[local_PP[i] - 1];
        for (i = 0; i < 32; i++)
            RR[i] = iu2b((b2iu(buffer[i]) + b2iu(ll[i])) & 1);
        for (i = 0; i < 32; i++)
            LL[i] = rr[i];
    }

    private void _Fiip(byte[] text, byte[] ll, byte[] rr) {
        int i;
        byte[] tmp = new byte[64];
        for (i = 0; i < 32; i++)
            tmp[i] = ll[i];
        for (i = 32; i < 64; i++)
            tmp[i] = rr[i - 32];
        for (i = 0; i < 64; i++)
            text[i] = tmp[_iip_tab_p[i] - 1];
    }

    // private void Fcompress016(byte[] out, byte[] in) {
    // int times;
    // int i, j;
    // for (i = 0; i < 16; i++) {
    // times = 3;
    // in[i] = '0';
    // for (j = 0; j < 4; j++) {
    // in[i] = iu2b(b2iu(in[i]) + (b2iu(out[i * 16 + j]) << times));
    // times--;
    // }
    // }
    // }

    void Fcompress0(byte[] out, byte[] in) {
        int times;
        int i, j;
        for (i = 0; i < 8; i++) {
            times = 7;
            in[i] = 0;
            for (j = 0; j < 8; j++) {
                in[i] = iu2b(b2iu(in[i]) + (b2iu(out[i * 8 + j]) << times));
                times--;
            }
        }
    }

    private void Fencrypt0(byte[] text, byte[] mtext) {
        byte[] ll = new byte[64], rr = new byte[64], LL = new byte[64], RR = new byte[64];
        byte[] tmp = new byte[64];
        int i, j;
        Fiip(text, ll, rr);
        for (i = 1; i < 17; i++) {
            FF(i, ll, rr, LL, RR);
            for (j = 0; j < 32; j++) {
                ll[j] = LL[j];
                rr[j] = RR[j];
            }
        }
        _Fiip(tmp, rr, ll);
        Fcompress0(tmp, mtext);
    }

    private void FDES(byte[] key, byte[] text, byte[] mtext) {
        byte[] tmp = new byte[64];
        Fexpand0(key, tmp);
        Fsetkeystar(tmp);
        Fencrypt0(text, mtext);
    }

    /* 加密 */
    public int ENCRYPT(byte[] key, byte[] s, byte[] d, int len) {
        int i, j;
        byte[] cData = new byte[8];
        byte[] cEncryptData = new byte[8];
        for (i = 0; i < len; i += 8) {
            if ((i + 8) > len) {
                desMemcpy(cData, s, 0, i, len - i);
                for (j = len - i; j < 8; j++)
                    cData[j] = 0;
            } else
                desMemcpy(cData, s, 0, i, 8);
            FDES(key, cData, cEncryptData);
            desMemcpy(d, cEncryptData, i, 0, 8);
        }
        return i;
    }

    private void Fdiscrypt0(byte[] mtext, byte[] text) {
        byte[] ll = new byte[64], rr = new byte[64], LL = new byte[64], RR = new byte[64];
        byte[] tmp = new byte[64];
        int i, j;
        Fiip(mtext, ll, rr);
        for (i = 16; i > 0; i--) {
            FF(i, ll, rr, LL, RR);
            for (j = 0; j < 32; j++) {
                ll[j] = LL[j];
                rr[j] = RR[j];
            }
        }
        _Fiip(tmp, rr, ll);
        Fcompress0(tmp, text);
    }

    /***************************************************************************
     * function: DES parameter: u_char * key ; key for encrypt u_char * mtext ;
     * encipher data u_char * text ; plain data return: none
     **************************************************************************/
    private void _FDES(byte[] key, byte[] mtext, byte[] text) {
        byte[] tmp = new byte[64];
        Fexpand0(key, tmp);
        Fsetkeystar(tmp);
        Fdiscrypt0(mtext, text);
    }

    /* 解密 */
    public int DECRYPT(byte[] key, byte[] s, byte[] d, int len) {
        int i;
        byte[] cData = new byte[8];
        byte[] cEncryptData = new byte[8];
        for (i = 0; i < len; i += 8) {
            desMemcpy(cEncryptData, d, 0, i, 8);
            _FDES(key, cEncryptData, cData);
            desMemcpy(s, cData, i, 0, 8);
        }
        return i;
    }

    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    //加密
    public String EncodeStr(String str, int len) {
        int i1;
        String digestHexStr;
        byte[] byte2 = new byte[len];
        byte[] byte1 = str.getBytes();
        i1 = ENCRYPT(strDefaultKey.getBytes(), byte1, byte2, byte1.length);
        digestHexStr = "";
        for (int i = 0; i < i1; i++) {
            digestHexStr += byteHEX(byte2[i]);
        }
        return digestHexStr;
    }

    //加密
    public byte[] Encode(byte[] byteMingWen, int len) {
        int i1;
        byte[] dataByte = new byte[len + 8];
        i1 = ENCRYPT(strDefaultKey.getBytes(), byteMingWen, dataByte, len);
        byte[] byteMiW = new byte[i1];
        System.arraycopy(dataByte, 0, byteMiW, 0, i1);
        Log.d(TAG, "miWen = " + EncodingUtils.getString(byteMiW, "UTF-8"));
        return byteMiW;
    }

    //解密
    public String DecodeStr(String str, int len) {
        int i2;
        byte[] byteMingW = new byte[len]; // 密文
        byte[] byteMiW;
        DesEncryption m = new DesEncryption();
        byteMiW = DesEncryption.hexStr2ByteArr(str);
        i2 = m.DECRYPT(DesEncryption.strDefaultKey.getBytes(), byteMingW,byteMiW, byteMiW.length);
        return new String(byteMingW, 0, i2);
    }

    public byte[] Decode(byte[] byteMiWen, int len) {
        int i2;
        byte[] dataByte = new byte[len + 8]; // 密文
        DesEncryption m = new DesEncryption();
        i2 = m.DECRYPT(DesEncryption.strDefaultKey.getBytes(), dataByte,byteMiWen, len);
        byte[] byteMingW = new byte[i2];
        System.arraycopy(dataByte, 0, byteMingW, 0, i2);
        Log.d(TAG, "mingWen = " + EncodingUtils.getString(byteMingW, "UTF-8"));
        return byteMingW;
    }
}
