package com.sinotn.examsafety.po;

/**
 * ResultExamSum entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class ResultExamSum implements java.io.Serializable {

	// Fields

	private Long id;
	private ExamPlace examPlace;
	private String subject;
	private Integer total;
	private Integer passSum;
	private Integer passNormalSum;
	private Integer passSpecialSum;
	private Integer passExitSum;
	private Integer photoSumY;
	private Integer photoSumN;
	private Integer photoSumNn;
	private Integer photoSumNy;
	private Integer identitySumY;
	private Integer identitySumN;
	private Integer cardSum;
	private Integer realSum;

	// Constructors

	/** default constructor */
	public ResultExamSum() {
	}

	/** minimal constructor */
	public ResultExamSum(Long id, ExamPlace examPlace) {
		this.id = id;
		this.examPlace = examPlace;
	}

	/** full constructor */
	public ResultExamSum(Long id, ExamPlace examPlace, String subject,
			Integer total, Integer passSum, Integer passNormalSum,
			Integer passSpecialSum, Integer passExitSum, Integer photoSumY,
			Integer photoSumN, Integer photoSumNn, Integer photoSumNy,
			Integer identitySumY, Integer identitySumN, Integer cardSum,
			Integer realSum) {
		this.id = id;
		this.examPlace = examPlace;
		this.subject = subject;
		this.total = total;
		this.passSum = passSum;
		this.passNormalSum = passNormalSum;
		this.passSpecialSum = passSpecialSum;
		this.passExitSum = passExitSum;
		this.photoSumY = photoSumY;
		this.photoSumN = photoSumN;
		this.photoSumNn = photoSumNn;
		this.photoSumNy = photoSumNy;
		this.identitySumY = identitySumY;
		this.identitySumN = identitySumN;
		this.cardSum = cardSum;
		this.realSum = realSum;
	}

	// Property accessors

	public ResultExamSum(Integer passSum, Integer passNormalSum,
			Integer passSpecialSum, Integer passExitSum, Integer photoSumY,
			Integer photoSumN, Integer photoSumNn, Integer photoSumNy,
			Integer identitySumY, Integer identitySumN, Integer cardSum,
			Integer realSum) {
		super();
		this.passSum = passSum;
		this.passNormalSum = passNormalSum;
		this.passSpecialSum = passSpecialSum;
		this.passExitSum = passExitSum;
		this.photoSumY = photoSumY;
		this.photoSumN = photoSumN;
		this.photoSumNn = photoSumNn;
		this.photoSumNy = photoSumNy;
		this.identitySumY = identitySumY;
		this.identitySumN = identitySumN;
		this.cardSum = cardSum;
		this.realSum = realSum;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExamPlace getExamPlace() {
		return this.examPlace;
	}

	public void setExamPlace(ExamPlace examPlace) {
		this.examPlace = examPlace;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPassSum() {
		return this.passSum;
	}

	public void setPassSum(Integer passSum) {
		this.passSum = passSum;
	}

	public Integer getPassNormalSum() {
		return this.passNormalSum;
	}

	public void setPassNormalSum(Integer passNormalSum) {
		this.passNormalSum = passNormalSum;
	}

	public Integer getPassSpecialSum() {
		return this.passSpecialSum;
	}

	public void setPassSpecialSum(Integer passSpecialSum) {
		this.passSpecialSum = passSpecialSum;
	}

	public Integer getPassExitSum() {
		return this.passExitSum;
	}

	public void setPassExitSum(Integer passExitSum) {
		this.passExitSum = passExitSum;
	}

	public Integer getPhotoSumY() {
		return this.photoSumY;
	}

	public void setPhotoSumY(Integer photoSumY) {
		this.photoSumY = photoSumY;
	}

	public Integer getPhotoSumN() {
		return photoSumN;
	}

	public void setPhotoSumN(Integer photoSumN) {
		this.photoSumN = photoSumN;
	}

	public Integer getPhotoSumNn() {
		return this.photoSumNn;
	}

	public void setPhotoSumNn(Integer photoSumNn) {
		this.photoSumNn = photoSumNn;
	}

	public Integer getPhotoSumNy() {
		return this.photoSumNy;
	}

	public void setPhotoSumNy(Integer photoSumNy) {
		this.photoSumNy = photoSumNy;
	}

	public Integer getIdentitySumY() {
		return this.identitySumY;
	}

	public void setIdentitySumY(Integer identitySumY) {
		this.identitySumY = identitySumY;
	}

	public Integer getIdentitySumN() {
		return this.identitySumN;
	}

	public void setIdentitySumN(Integer identitySumN) {
		this.identitySumN = identitySumN;
	}

	public Integer getCardSum() {
		return this.cardSum;
	}

	public void setCardSum(Integer cardSum) {
		this.cardSum = cardSum;
	}

	public Integer getRealSum() {
		return this.realSum;
	}

	public void setRealSum(Integer realSum) {
		this.realSum = realSum;
	}

}